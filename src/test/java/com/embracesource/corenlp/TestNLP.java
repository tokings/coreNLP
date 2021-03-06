package com.embracesource.corenlp;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import edu.stanford.nlp.coref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class TestNLP {

	@Test
	public void test() {
		// 构造一个StanfordCoreNLP对象，配置NLP的功能，如lemma是词干化，ner是命名实体识别等
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// 待处理字符串
		String text = "judy has been to China . she likes people there . and she went to Beijing ";// Add
		// 创造一个空的Annotation对象
		Annotation document = new Annotation(text);
		// 对文本进行分析
		pipeline.annotate(document);

		// 获取文本处理结果
		System.out.println("文本处理结果");
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			String word1 = sentence.get(TextAnnotation.class);
			System.out.println(word1);
			
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				
				System.out.println("token=============");
				System.out.println("after:" + token.after());
				System.out.println("before:" + token.before());
				System.out.println("beginPosition:" + token.beginPosition());
				System.out.println("category:" + token.category());
				System.out.println("docID:" + token.docID());
				System.out.println("endPosition:" + token.endPosition());
				System.out.println("index:" + token.index());
				System.out.println("lemma:" + token.lemma());
				System.out.println("ner:" + token.ner());
				System.out.println("originalText:" + token.originalText());
				System.out.println("size:" + token.size());
				System.out.println("tag:" + token.tag());
				System.out.println("toString:" + token.toString());
				System.out.println("value:" + token.value());
				System.out.println("word:" + token.word());
				
				System.out.println("======");
				// 获取句子的token（可以是作为分词后的词语）
				String word = token.get(TextAnnotation.class);
				System.out.println(word);
				// 词性标注
				String pos = token.get(PartOfSpeechAnnotation.class);
				System.out.println(pos);
				// 命名实体识别
				String ne = token.get(NamedEntityTagAnnotation.class);
				System.out.println(ne);
				// 词干化处理
				String lema = token.get(LemmaAnnotation.class);
				System.out.println(lema);
			}

			// 句子的解析树
			System.out.println("句子解析树");
			Tree tree = sentence.get(TreeAnnotation.class);
			tree.pennPrint();

			// 句子的依赖图
			System.out.println("句子依赖图");
			SemanticGraph graph = sentence
					.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
			System.out.println(graph.toString(SemanticGraph.OutputFormat.LIST));

		}

		// 指代词链
		// 每条链保存指代的集合
		// 句子和偏移量都从1开始
		Map<Integer, CorefChain> corefChains = document.get(CorefChainAnnotation.class);
		if (corefChains == null) {
			return;
		}
		for (Map.Entry<Integer, CorefChain> entry : corefChains.entrySet()) {
			System.out.println("Chain " + entry.getKey() + " ");
			for (CorefChain.CorefMention m : entry.getValue().getMentionsInTextualOrder()) {
				// We need to subtract one since the indices count from 1 but
				// the Lists start from 0
				List<CoreLabel> tokens = sentences.get(m.sentNum - 1).get(CoreAnnotations.TokensAnnotation.class);
				// We subtract two for end: one for 0-based indexing, and one
				// because we want last token of mention not one following.
				System.out.println(
						"  " + m + ", i.e., 0-based character offsets [" + tokens.get(m.startIndex - 1).beginPosition()
								+ ", " + tokens.get(m.endIndex - 2).endPosition() + ")");
			}
		}
	}

}
