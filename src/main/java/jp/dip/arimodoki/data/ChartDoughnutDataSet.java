package jp.dip.arimodoki.data;

/**
 * Char.js　グラフdatasets情報格納Beanクラス
 */
public class ChartDoughnutDataSet {
    /** データ値 */
    private int[] data = null;

    /** グラフ背景色 */
    private String[] backgroundColor = null;

    /** onmouse時グラフ背景色 */
    private String[] hoverBackgroundColor = null;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String[] getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String[] getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(String[] hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }
}
