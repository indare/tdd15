
/**
 * Created by IntelliJ IDEA.
 * User: m_arino
 * Date: 12/02/19
 * Time: 1:32
 * To change this template use File | Settings | File Templates.
 */
class TddOdai {
    private static final List CASHS = [1000, 500, 100, 50, 10];
    def insertedMoney = [0]
    def sales = 0
    def goodsName = [0:"NONE", 1:"コーラ"]
    def goodsStock = [0:0, 1:5]
    def goodsPrice = [0:Integer.MAX_VALUE, 1:120]

    def insertCash(cash){
        def returnCode = CASHS.contains(cash)
        if(returnCode){
            insertedMoney.add(cash)
        }else{
            throw new IllegalArgumentException()
        }
        returnCode
    }

    def getSum(){
        insertedMoney.sum()
    }

    def showSales(){
        sales
    }

    def registerSales(sales){
        this.sales += sales
    }

    def getName(ID){
        goodsName[ID]
    }

    def getCount(ID){
        goodsStock[ID]
    }

    def getPrice(ID){
        goodsPrice[ID]
    }

    def canBuy(cash){
        if(cash == 0) return 0
        goodsPrice.find {it.value <= cash}.key
    }

    def buy(ID){
        if (goodsStock[ID]==0) { return false }
        goodsStock[ID] -= 1
        true
    }
}
