package jp.dip.arimodoki.common;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * JSONとJavaオブジェクトのコンバートを行うクラスです。
 * 任意のJSONからJavaオブジェクトへのデシリアライズ（整列化）
 * 任意のJavaオブジェクトからJSONへのシリアライズ（直列化）
 * を行います。
 * gson-x.x.x.jar　必須
 */

@Scope("prototype")
@Component
public class JsonConvert implements JsonConvertIf {
	/*Google Gsonオブジェクト生成*/
	Gson mygson = new Gson();

	 /**
     *  Deserialization
     * JSON文字列を 定義済みのJavaオブジェクトへデシリアライズする
     * @param jsonData デシリアライズするJSONフォーマット文字列
     * @param parseObj デシリアライズ結果を格納するJavaオブジェクト(Class)
     * 　　　　　　　　　オブジェクト(Class)の型は、JSONフォーマット構造を包含している必要がある。
     * @return 変換結果を格納したJavaオブジェクトを返す(==obj)　エラーの場合はnullを返す
     * @throws Exception
     */
	public Object Deserialize(String jsonData,Object parseObj)throws Exception{
		//対象のJSON文字列が無い場合はエラー
        if(jsonData == null || jsonData.equals("")) return null;
        //デシリアライズ結果を格納するJavaオブジェクトがnullの場合はエラー
        if(parseObj == null) return null;

        InputStreamReader isr = new InputStreamReader( new ByteArrayInputStream(jsonData.getBytes()));
        JsonReader jsr = new JsonReader( isr );
        return (Object) mygson.fromJson( jsr, parseObj.getClass() );
	}

	/**
     * Serialization
     * JavaオブジェクトをJSONフォーマット文字列にシリアライズする
     * @param obj シリアライズ対象クラスオブジェクト
     * @return クラス構造がパースされた、JSONフォーマット文字列を返す　エラーの場合はnullを返す
     * @throws Exception
     */
    public String Serialize(Object obj) throws Exception {
        //シリアライズ対象クラスオブジェクトがnullの場合はエラー
        if(obj == null) return null;

        return mygson.toJson(obj);
    }

    /**
     * URLエンコードされたJSON文字列をdecodeする

     * 文字コードはUTF-8決め打ちとする
     * @param encStr URLエンコードされた文字列
     * @return decodeされた文字列を返す　エラーの場合はnullを返す
     * @throws Exception
     */
    public String decode(String encStr) throws Exception {
        //エンコードされた文字列が無い場合はエラー
        if(encStr == null || encStr.equals("")) return null;

        return this.decode(encStr,"UTF-8");
    }

    /**
     * URLエンコードされたJSON文字列をdecodeする
     * JSON文字コードはUTF-8を期待する（その他の文字コードでの保証はなし）
     * @param encStr URLエンコードされた文字列
     * @param charcode decodeする文字コードを指定する
     * @return decodeされた文字列を返す　エラーの場合はnullを返す
     * @throws Exception
     */
    public String decode(String encStr, String charcode) throws Exception {
        //エンコードされた文字列が無い場合はエラー
        if(encStr == null || encStr.equals("")) return null;
        //文字コードが無い場合はエラー
        if(charcode == null || charcode.equals("")) return null;

        String decStr = "";
        //エンコード文字列のデコード
        String decparam = URLDecoder.decode(encStr,charcode);
        int len = decparam.length();
        int last = decparam.lastIndexOf('=');
        if(last == len-1) {     //末尾が = の場合 (ブラウザからJSONが送られてきた場合
            decStr = decparam.substring(0, len-1);
        } else {                        //末尾 != = ならそのまま返却
            decStr = decparam;
        }
        return decStr;
    }

}