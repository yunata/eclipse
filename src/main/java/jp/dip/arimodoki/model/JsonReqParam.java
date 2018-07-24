package jp.dip.arimodoki.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * サーブレットリクエストで渡されるJSON形式の
 * リクエストパラメータをパースして格納するデータモデルクラスです
 */
@Scope("prototype")			//singleton回避
@Component				//コンポーネントスキャン用のおまじない
public class JsonReqParam implements JsonReqParamIf {

	/**
	 * 分類ID
	 */
	private int category;

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}