package code;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public class ShoppingList {

    private Vector<ShoppingTerm> shoplist;
    int offerGoodKinds = 0;

    public ShoppingList(String inputStr, Map<String, Good> goods, Vector<Promotion> goodoffer) {
        // TODO Auto-generated constructor stub

        shoplist = new Vector<ShoppingTerm>();
        Vector<String> goodinfo = parseData(inputStr);

        for (String str : goodinfo) {
            int inter = str.indexOf('-');
            String barcode = str.substring(0, inter);
            int num = Integer.parseInt(str.substring(inter + 1));

            Good curGood = goods.get(barcode);
            ShoppingTerm term = new ShoppingTerm(curGood, num);
            shoplist.add(term);
        }
        performPromotion(goodoffer);
    }

    /**
     * 优惠信息计算
     *
     * @param goodoffer
     */
    public void performPromotion(Vector<Promotion> goodoffer) {
        for (Promotion p : goodoffer) {
            if (p.getOfferType().equals("Discount_By_Type")) {
                judgeOffer(p.getBarcodes());
            }
        }
    }

    /**
     * 打印购物清单
     */
    public void printShoppingList() {
        DecimalFormat df = new DecimalFormat("######0.00");
        double total = 0.0;
        double save = 0.0;

        System.out.println("*<没钱赚商店>购物清单*");
        for (ShoppingTerm term : shoplist) {
            term.printShoppingTerm();
            total += term.getsubtotal();
            save += term.getdiscount();
        }
        if (offerGoodKinds > 0) {
            System.out.println("---------------------------------------------");
            System.out.println("批发价出售商品：");
            for (ShoppingTerm term : shoplist) {
                term.printOfferTerm();
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println("总计：" + df.format(total) + "(元)");

        if (offerGoodKinds > 0) {
            System.out.println("节省：" + df.format(save) + "(元)");
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * 满足自身为优惠商品，所属子类数目大于等于10，95折优惠
     *
     * @param goodoffer
     */
    private void judgeOffer(Vector<String> goodoffer) {
        Map<String, Integer> offerinfo = new HashMap<String, Integer>();

        for (ShoppingTerm term : shoplist) {

            String barcode = term.getGood().getBarcode();
            int index = goodoffer.indexOf(barcode);
            if (index == -1) {
                term.setOffer(1.0);
                continue;
            }
            String subcat = term.getGood().getSubCategory();
            int num = term.getNum();
            if (offerinfo.get(subcat) != null) {
                offerinfo.put(subcat, (int) offerinfo.get(subcat) + num);
            } else {
                offerinfo.put(subcat, num);
            }
        }

        //遍历设置优惠信息
        Iterator entries = offerinfo.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Integer value = (Integer) entry.getValue();
            if (value >= 10) {

                String key = (String) entry.getKey();
                for (ShoppingTerm term : shoplist) {
                    if (term.getOffer() > 0)
                        continue;

                    if (term.getGood().getSubCategory().equals(key)) {
                        term.setOffer(0.95);
                        offerGoodKinds++;
                    }
                }
            }
        }

        for (ShoppingTerm term : shoplist) {
            if (term.getOffer() < 1e-8)
                term.setOffer(1.0);
        }
    }

    /**
     * 解析输入条形码数组
     *
     * @param inputStr
     * @return
     */
    private Vector<String> parseData(String inputStr) {
        Vector<String> res = new Vector<String>();
        int curs = inputStr.indexOf('\'');
        while (curs != -1) {
            int curt = inputStr.indexOf('\'', curs + 1);
            String curStr = inputStr.substring(curs + 1, curt);
            res.add(curStr);
            curs = inputStr.indexOf('\'', curt + 1);
        }
        return res;
    }
}
