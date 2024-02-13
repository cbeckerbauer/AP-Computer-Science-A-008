
public class BookRunner {

	public static void main(String[] args) {
		
		BeckerbauerBookAnalytics anInstance = new BeckerbauerBookAnalytics("A_Modest_Proposal.txt", "A_Modest_Proposal_Edit.txt");
		
		anInstance.printFile(); 
		String[] wordsToBeReplacedFirst = {"children", "kingdom", "thousand", "country", "therefore"};
		String[] wordsToReplaceWithFirst = {"extract", "fibre", "executrix", "bike", "peanut"};
		anInstance.replaceWordsAndWriteToFile(wordsToBeReplacedFirst, wordsToReplaceWithFirst);
		
		BeckerbauerBookAnalytics aInstance = new BeckerbauerBookAnalytics("A_Modest_Proposal.txt", "A_Modest_Proposal_Edit_Two.txt");
		
		String[] wordsToBeReplacedSecond = {"would", "one", "upon", "number", "may"};
		String[] wordsToReplaceWithSecond = {"close", "physical", "position", "nose", "flavor"};
		aInstance.replaceWordsAndWriteToFile(wordsToBeReplacedSecond, wordsToReplaceWithSecond);

	}

}
