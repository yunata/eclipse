package jp.dip.arimodoki.cntl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.dip.arimodoki.blogic.BlChartIf;
import jp.dip.arimodoki.common.CConst;
import jp.dip.arimodoki.common.JsonConvertIf;
import jp.dip.arimodoki.model.FormChartIf;
import jp.dip.arimodoki.model.JsonReqParamIf;

/**
 * ■JSONサンプル　ドーナツグラフ描画
 */
@Scope("prototype")
@RestController
public class DoughnutChart implements CConst {
    //JSON　パーサー/ジェネレータクラスインスタンスを生成
    @Autowired
    private JsonConvertIf jsonConvert;

    //FormBean
    @Autowired
    private FormChartIf formChart;

    @Qualifier("BlChartMoc")          //名前付きビジネスロジッククラスをDI
    @Autowired
    private BlChartIf   blChart;        //グラフ情報処理ビジネスロジックDI

    //JSONパース結果格納クラスインスタンスを生成
    @Autowired
    private JsonReqParamIf jsonReqParam;


    /**
     * データモデルへのバインド
     */
    @ModelAttribute("FormChart")
    public FormChartIf setUpBindObject() {
        //FormBeanをビジネスロジックに継承。
        this.blChart.setForm(this.formChart);

        return this.formChart;
    }

    /**
    * チャートサンプル　単純にview(グラフサンプル初期画面)を表示する
    * @RestController コントローラのメソッドの戻り値は
    * 　通常は単純文字列を返すので、viewオブジェクトを返したい場合は
    * 　ModelAndViewを返すようにしなければならない
    */
    @RequestMapping(value = "/doughnutchart")
    public ModelAndView doughnutchart() {
        //チャート描画ページを表示するだけ
        return new ModelAndView("chart");
    }

    /**
     * ■ドーナツグラフ情報を生成し、viewに返却する
     * view(HTML)のAjaxからコールされるリクエストメソッド
     */
    @RequestMapping(value = "/doughnutchartdraw",
            //Response結果の全角が文字化けするのを防ぐおまじない
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            //以下は指定してもダメです
//          produces = "text/plain;charset=UTF-8"
//          produces = "application/json;charset=UTF-8"
        )
     public String doughnutchartdraw(
        @RequestBody String jParam          //JSON形式パラメータがencodeされてくる
        ) throws Exception {

        //URLencodeされて来るのでUTF-8でdecodeする
        jParam = jsonConvert.decode(jParam);
        //JSON表現形式をJavaオブジェクト(jsonReqParam)にデシリアライズする
        jsonReqParam = (JsonReqParamIf)jsonConvert.Deserialize(jParam, jsonReqParam);

        //変換されたパラメータオブジェクトをFormBeanにセットする
        formChart.setJsonReqParam(jsonReqParam);

        //ドーナツグラフ情報を生成する（モック固定）
        blChart.getCharDoughnutData();

        //ドーナツグラフ情報javaオブジェクトをJSON表現にシリアライズする
        String resp_view= jsonConvert.Serialize(formChart.getChartDoughnutData());
        //返却JSON文字列をロギングしてみる
        logger.log_info(this, "resp_view["+resp_view+"]");

        return resp_view;       //JSON表現形式を返却する
    }
}


















