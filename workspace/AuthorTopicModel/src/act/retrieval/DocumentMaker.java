package act.retrieval;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

/**
 * A utility for making Lucene Documents from a File.
 * 
 * @author wanghan
 * 
 */
public class DocumentMaker {

	public static Document getPaperDocument(String title, int length,
			String conf, String contents) throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.Title, title, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.ConferenceName, conf, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.Length, String.valueOf(length),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, contents, Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		// return the document

		return doc;
	}

	public static Document getAuthorDocument(String authorName,String authorIndex, int length, String contents) throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.AuthorName, authorName, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.AuthorACMIndex, authorIndex, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.Length, String.valueOf(length),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, contents, Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		// return the document

		return doc;
	}

	public static Document getConfDocument(String confName,int conIndex, int length, String contents) throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.ConferenceName, confName, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.ConferenceIndex, String.valueOf(conIndex), Field.Store.YES,
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
