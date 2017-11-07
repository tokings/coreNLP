package com.embracesource.corenlp;

import java.io.IOException;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

public class TestNER {

	private static String str = "李明 去 吃饭 ， 告诉 李强 一声 ， 叫他 不要 傻等 习近平 。 我 爱 北京 天安门 ， 天安门 向 太阳 升 ！ 伟大 领袖 毛主席 ， 指引 我们 向前 冲 ！";
	private static AbstractSequenceClassifier<CoreLabel> ner;
	
	static {
		String serializedClassifier = "classifiers/chinese.misc.distsim.crf.ser.gz"; // chinese.misc.distsim.crf.ser.gz
		try {
			ner = CRFClassifier.getClassifier(serializedClassifier);
		} catch (ClassCastException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(doNer(str));
	}

	public static String doNer(String sent) {
		return ner.classifyWithInlineXML(sent);
	}

}
