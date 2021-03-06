package com.embracesource.corenlp;

import org.junit.Test;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class TestChinese {

	@Test
	public void test() {
		String text = "马飚向伦古转达了习近平主席的诚挚祝贺和良好祝愿。"
				+ "马飚表示，中国和赞比亚建交50多年来，双方始终真诚友好、平等相待，友好合作结出累累硕果，给两国人民带来了实实在在的利益。"
				+ "中方高度重视中赞关系发展，愿以落实两国元首共识和中非合作论坛约翰内斯堡峰会成果为契机，推动中赞关系再上新台阶。";
		
		text = "【学习进行时】10月发生的最重大事件，就是党的十九次全国代表大会胜利举行。本月总书记的所有活动，几乎都与十九大相关。"
				+ "新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，以“十九大”为关键词，为您梳理习近平10月份的重要活动。";

		text = "14次会议活动、9次致电致信、2次外事、1次重要批示……金秋十月，习近平总书记格外忙碌。用一个关键词来定义习近平的10月，那就是“十九大”。十九大：从“新”开始";
		
		text = "10月18日到24日，中国共产党召开第十九次全国代表大会。七天很短，却意义非凡，注定要在中国发展史上留下深刻烙印。"
				+ "十九大，是一次总结过去、研判现在、谋划未来的大会，是在全面建成小康社会决胜阶段、中国特色社会主义进入新时代的关键时期召开的一次十分重要的大会。正如习近平所说，十九大制定了党和国家未来发展的总体方针和行动纲领，反映着全党8900万党员的高度共识，具有特殊重要的意义。"
				+ "“十九大”成为国际媒体报道热词。俄罗斯《独立报》评论文章说:“如果没有中国共产党领导，无法想象中国能够取得如此成就。”路透社报道说，如果说五年前习近平提出的“中国梦”只有一个初步轮廓，那么五年后的今天，十九大报告中对“中国梦”给出了更详细的实现路径……中央党校原副校长李君如在接受新华网记者采访时指出，十九大历史地位格外突出。国际媒体的争相报道，为这句话做了最好的注脚。"
				+ "在十九大开幕会上，习近平作了题为《决胜全面建成小康社会 夺取新时代中国特色社会主义伟大胜利》的报告。报告描绘了决胜全面建成小康社会、夺取新时代中国特色社会主义伟大胜利的宏伟蓝图，进一步指明了党和国家事业的前进方向，是全党全国各族人民智慧的结晶，是我们党团结带领全国各族人民在新时代坚持和发展中国特色社会主义的政治宣言和行动纲领，是马克思主义的纲领性文献。"
				+ "三万多字的报告，其最大特点是什么？是“新”。中央党校教授韩庆祥用12个“新”字加以概括：历史新变革、时代新开辟、矛盾新内涵、历史新使命、实践新路径、时代新课题、成果新概括、理论新飞跃、实践新方略、建党新思路、历史新意义、表述新界定。"
				+ "新意盎然，从“新”开始。雄关漫道真如铁，而今迈步从头越。十九大，吹响了号角。风华正茂的中国共产党带领着意气风发的中国，行进在希望的田野上。";
	
		Annotation annotation = new Annotation(text);
		StanfordCoreNLP corenlp = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
		corenlp.annotate(annotation);
		
		corenlp.prettyPrint(annotation, System.out);
	}

}
