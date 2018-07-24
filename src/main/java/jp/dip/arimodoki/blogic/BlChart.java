

package jp.dip.arimodoki.blogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import jp.dip.arimodoki.common.CConst;
import jp.dip.arimodoki.common.JsonConvertIf;
import jp.dip.arimodoki.model.FormChartIf;
import jp.dip.arimodoki.model.data.ChartDoughnutData;
import jp.dip.arimodoki.model.data.ChartDoughnutDataSet;

/**
* ドーナツグラフ描画情報を処理するビジネスロジッククラスです。
*/
@Scope("prototype")         //singleton回避
@Service("BlChartMoc")     //名前付きアノテーション
public class BlChart implements CConst, BlChartIf {

    //JSON　パーサー/ジェネレータインスタンスを生成
    @Autowired
    private JsonConvertIf jsonConvert;

    //Chart情報フォームビーン(コントローラから継承される)
    private FormChartIf             formChart;

    /**
     * 以下、ドーナツグラフ描画情報（固定値）
     */
    //グラフの塗りつぶし色
    private static final String[][] fillColor = {
        {
        "#648dc7",      //アボカド
        "#00b1ae",      //キーウィ
        "#f7c35f",          //オレンジ
        "#eb714d",      //リンゴ
        "#ee71a1",      //モモ
        "#add673",      //マスカット
        "#4abdf0",      //スイカ
        "#b1629f"           //キョホウ
        },{
        "#f7c35f",          //豚肉
        "#648dc7",      //牛肉
        "#ee71a1",      //魚介
        "#00b1ae",      //鶏肉
        "#b1629f",      //鹿肉
        "#eb714d",      //馬肉
        "#4abdf0",      //鯨肉
        "#add673"       //モツ
        }
    };
    //グラフのラベル名称（フルーツ名／肉類名）
    private static final String[][] labelName = {
            {
            "アボカド",
            "キーウィ",
            "オレンジ",
            "リンゴ",
            "モモ",
            "マスカット",
            "スイカ",
            "キョホウ"
            },{
            "豚肉",
            "牛肉",
            "魚介",
            "鶏肉",
            "鹿肉",
            "馬肉",
            "鯨肉",
            "モツ"
            }
        };
    //グラフの値
    private static final int[][] dataValue = {
        {             //果物
            10,
            30,
            50,
            70,
            90,
            65,
            40,
            15
        },{       //肉
            10,
            20,
            30,
            40,
            50,
            60,
            70,
            85
        }
    };

    /**
     * フォームBeanの継承
     * コントローラで取得したFormBeanを継承する
     * @param form Chart情報フォームビーン
     */
    public void setForm(FormChartIf form) {
        this.formChart = form;
    }

    /**
     * ドーナツグラフ情報を生成する
     * ドーナツグラフ描画用JSONフォーマットに合わせた
     * 階層構造のオブジェクトを作成する
     * {"labels":["",..],"datasets":[{"data":["",..],"backgroundColor":["",..],"hoverBackgroundColor":["",..]]}]}
    */
    public void getCharDoughnutData() {
        //リクエストパラメータ（グラフ分類値）を取得
        int category = this.formChart.getJsonReqParam().getCategory();
        logger.log_info(this, "category["+category+"]");
        //ドーナツグラフ情報インスタンス生成
        ChartDoughnutData chartDoughnutData = new ChartDoughnutData();

        //ドーナツグラフdatasets情報インスタンス生成
        ChartDoughnutDataSet[] chartDoughnutDataSet = new ChartDoughnutDataSet[1];
        ChartDoughnutDataSet dataSet = new ChartDoughnutDataSet();
        chartDoughnutDataSet[0] = dataSet;

        //グラフの値をセット
        dataSet.setData(dataValue[category]);
        //グラフの塗りつぶし色をセット
        dataSet.setBackgroundColor(fillColor[category]);
        //onmouse時のグラフの塗りつぶし色をセット
        dataSet.setHoverBackgroundColor(fillColor[category]);

        //ラベル名称をセット
        chartDoughnutData.setLabels(labelName[category]);
        //ドーナツグラフdatasets情報をセット
        chartDoughnutData.setDatasets(chartDoughnutDataSet);

        //返却用FormBeanにドーナツグラフ情報を保持
        formChart.setChartDoughnutData(chartDoughnutData);
    }

}
