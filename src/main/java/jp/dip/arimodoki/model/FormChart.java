package jp.dip.arimodoki.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.dip.arimodoki.model.data.ChartDoughnutData;

/**
 * データ受け渡しフォームBeanクラス
 */
@Scope("prototype")         //singleton回避
@Component              //コンポーネントスキャン用のおまじない
public class FormChart implements FormChartIf {
    /**
     * リクエストパラメータ格納クラス
     */
    private JsonReqParamIf      jsonReqParam;

    /**
     * view返却用ドーナツグラフデータ
     */
    private ChartDoughnutData chartDoughnutData;

    public JsonReqParamIf getJsonReqParam() {
        return jsonReqParam;
    }

    public void setJsonReqParam(JsonReqParamIf jsonReqParam) {
        this.jsonReqParam = jsonReqParam;
    }

    public ChartDoughnutData getChartDoughnutData() {
        return chartDoughnutData;
    }

    public void setChartDoughnutData(ChartDoughnutData chartDoughnutData) {
        this.chartDoughnutData = chartDoughnutData;
    }

}