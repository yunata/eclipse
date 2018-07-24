package jp.dip.arimodoki.data;

/**
 * Chart.js グラフ描画情報返却用Beanクラス
 */
public class ChartDoughnutData {

    /** グラフラベル情報 */
    private String[] labels = null;

    /** グラフ描画情報(ChartDoughnutDataSet)の配列    */
    private ChartDoughnutDataSet[] datasets = null;

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}