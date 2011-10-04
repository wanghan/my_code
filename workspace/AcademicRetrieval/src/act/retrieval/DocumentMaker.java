package act.retrieval;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import actm.data.ACTMDocument;
import actm.data.Author;
import actm.data.Conference;

/**
 * A utility for making Lucene Documents from a File.
 * 
 * @author wanghan
 * 
 */
public class DocumentMaker {

	public static Document getPaperDocument(ACTMDocument paper) throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.TM_INDEX, String.valueOf(paper.getIndex()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.Title, paper.getPaper().getTitle(), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.DB_ID, String.valueOf(paper.getPaper().getId()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.Length, String.valueOf(paper.getBagOfWordSize()),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, paper.getPaper().getAbstractContent().toLowerCase(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		// return the document

		return doc;
	}

	public static Document getAuthorDocument(Author author, int length, String contents) throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.AuthorName, author.getName(), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.AuthorACMIndex, author.getAcmIndex(), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.TM_INDEX, String.valueOf(author.getIndex()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.DB_ID, String.valueOf(author.getId()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.Length, String.valueOf(length),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, contents, Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		// return the document

		return doc;
	}

	public static Document getConfDocument(Conference conference, int length, String contents) throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.ConferenceName, conference.getGlobalName(), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.ConferenceIndex,String.valueOf(conference.getGlobalIndex()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.DB_ID,String.valueOf(conference.getId()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.Length, String.valueOf(length),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, contents, Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		// return the document

		return doc;
	}
	
	private DocumentMaker() {
	}
}
