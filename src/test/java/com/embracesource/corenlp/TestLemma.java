package com.embracesource.corenlp;

import java.util.List;
import java.util.Properties;

import org.junit.Test;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class TestLemma {

	@Test
	public void test() {
		
		String input = "jack had been to china there months ago. he likes china very much,and he is falling love with this country";
		
		Properties props = new Properties();  
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");  
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);  
  
        Annotation document = new Annotation(input);  
        pipeline.annotate(document);  
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);  
  
        String output = "";  
        for (CoreMap sentence : sentences) {  
            // traversing the words in the current sentence  
            // a CoreLabel is a CoreMap with additional token-specific methods  
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {  
                String lema = token.get(LemmaAnnotation.class);  
                output += lema+" ";  
            }  
        }  
        
        System.out.print("原句    ：");  
        System.out.println(input);  
        System.out.print("词干化：");  
        System.out.println(output); 
	}

}
