package act.retrieval;

import java.io.Reader;

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
		doc.add(new Field(IndexFields.CONTENTS, paper.getPaper().getAbstract().toLowerCase(), Field.Store.YES,
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
		doc.add(new Field(IndexFields.ConferenceName, conference.getName(), Field.Store.YES,
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
	
	public static Document Document(String id, String filename,
			String orifilepath, String contents,String lfFilePath)
			throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.ID, id, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.FILENAME, filename, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.FILEPATH, orifilepath, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.LF_FILEPATH, lfFilePath, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, contents, Field.Store.YES,
				Field.Index.ANALYZED));
		
		// return the document
		
		return doc;
	}

	public static Document Document(String id, String filename,
			String orifilepath, Reader reader,String lfFilePath)
			throws java.io.FileNotFoundException {

		// make a new, empty document
		Document doc = new Document();
		doc.add(new Field(IndexFields.ID, id, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.FILENAME, filename, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.FILEPATH, orifilepath, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.LF_FILEPATH, lfFilePath, Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field(IndexFields.CONTENTS, reader));
		
		// return the document
		return doc;
	}
	
	private DocumentMaker() {
	}
}
