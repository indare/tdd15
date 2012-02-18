import org.junit.Test
import org.junit.Before
import spock.lang.Specification
/**
 * Created by IntelliJ IDEA.
 * User: m_arino
 * Date: 12/02/19
 * Time: 1:28
 * To change this template use File | Settings | File Templates.
 */
class TddOdaiTest extends Specification {
    TddOdai tddOdai

    def setup(){
        tddOdai = new TddOdai()
    }

    def"ID1は5本ある"(){
        expect :
        tddOdai.getCount(1) == 5
    }

    def"ID1はコーラ"(){
        expect :
        tddOdai.getName(1) == "コーラ"
    }

    def"ID1は1本120円"(){
        expect :
        tddOdai.getPrice(1) == 120
    }

    def "お金を投入できること"(){
        expect :
            tddOdai.insertCash(cash) == result
        where:
            cash    |result
            1000    |true
            500     |true
            100     |true
            50      |true
            10      |true
    }

    def "お金を投入できないこと"(){
        when :
            tddOdai.insertCash(cash) == result
        then :
            thrown(IllegalArgumentException)
        where:
            cash    | result
            10000   | false
            5000    | false
            2000    | false
            5       | false
            1       | false
    }

    def "お金を投入していない時は合計が0"(){
        expect :
            tddOdai.getSum() == 0
    }

    def "合計金額が算出できること"(){
        given:
            tddOdai.insertCash(1000)

        expect :
           tddOdai.getSum() == 1000
    }

    def "合計金額が算出できること2"(){
        given:
            tddOdai.insertCash(1000)
            tddOdai.insertCash(500)
            tddOdai.insertCash(100)
            tddOdai.insertCash(100)

        expect :
            tddOdai.getSum() == 1700
    }

    def"0円は何も買えない"(){
        expect :
        tddOdai.canBuy(tddOdai.getSum()) == 0
    }

    def"120円はID1が買える"(){
        given :
            tddOdai.insertCash(100)
            tddOdai.insertCash(10)
            tddOdai.insertCash(10)
        expect :
            tddOdai.canBuy(tddOdai.getSum()) == 1
    }

    def "買える"(){
        given:
        tddOdai.insertCash(500)
        tddOdai.buy(1)
        expect:
        tddOdai.getCount(1) == 4
    }

    def "売上金額がわかる"(){
        given:
            tddOdai.insertCash(500)
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
        expect:
            tddOdai.showSales() == 120

    }

    def "在庫数に売上が反映される"() {
        given :
            tddOdai.insertCash(500)
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
        expect:
            tddOdai.getCount(1) == 4
    }

    def "在庫が減った"(){
        given :
            def prevStock = tddOdai.getCount(1)
            tddOdai.insertCash(500)
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
        expect:
            prevStock == tddOdai.getCount(1) + 1
    }
    
    def "在庫が切れたら買えない" () {
        given :
            tddOdai.insertCash(1000)
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
            tddOdai.buy(1)
            tddOdai.registerSales(tddOdai.getPrice(1))
        
        expect :
            !tddOdai.buy(1)
    }
}
