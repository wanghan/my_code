/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   SmartUpload.java

package com.jspsmart.upload;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.File;

// Referenced classes of package com.jspsmart.upload:
//            Files, Request, SmartUploadException, File

public class SmartUpload
{

    public SmartUpload()
    {
        m_totalBytes = 0;
        m_currentIndex = 0;
        m_startData = 0;
        m_endData = 0;
        m_boundary = new String();
        m_totalMaxFileSize = 0L;
        m_maxFileSize = 0L;
        m_deniedFilesList = new Vector();
        m_allowedFilesList = new Vector();
        m_denyPhysicalPath = false;
        m_forcePhysicalPath = false;
        m_contentDisposition = new String();
        m_files = new Files();
        m_formRequest = new Request();
    }

    /**
     * @deprecated Method init is deprecated
     */

    public final void init(ServletConfig config)
        throws ServletException
    {
        m_application = config.getServletContext();
    }

    /**
     * @deprecated Method service is deprecated
     */

    public void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        m_request = request;
        m_response = response;
    }

    public final void initialize(ServletConfig config, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        m_application = config.getServletContext();
        m_request = request;
        m_response = response;
    }

    public final void initialize(PageContext pageContext)
        throws ServletException
    {
        m_application = pageContext.getServletContext();
        m_request = (HttpServletRequest)pageContext.getRequest();
        m_response = (HttpServletResponse)pageContext.getResponse();
    }

    /**
     * @deprecated Method initialize is deprecated
     */

    public final void initialize(ServletContext application, HttpSession session, HttpServletRequest request, HttpServletResponse response, JspWriter out)
        throws ServletException
    {
        m_application = application;
        m_request = request;
        m_response = response;
    }

    public void upload()
        throws SmartUploadException, IOException, ServletException
    {
        int totalRead = 0;
        int readBytes = 0;
        long totalFileSize = 0L;
        boolean found = false;
        String dataHeader = new String();
        String fieldName = new String();
        String fileName = new String();
        String fileExt = new String();
        String filePathName = new String();
        String contentType = new String();
        String contentDisp = new String();
        String typeMIME = new String();
        String subTypeMIME = new String();
        boolean isFile = false;
        m_totalBytes = m_request.getContentLength();
        m_binArray = new byte[m_totalBytes];
        for(; totalRead < m_totalBytes; totalRead += readBytes)
            try
            {
                m_request.getInputStream();
                readBytes = m_request.getInputStream().read(m_binArray, totalRead, m_totalBytes - totalRead);
            }
            catch(Exception e)
            {
                throw new SmartUploadException("Unable to upload.");
            }

        for(; !found && m_currentIndex < m_totalBytes; m_currentIndex++)
            if(m_binArray[m_currentIndex] == 13)
                found = true;
            else
                m_boundary = m_boundary + (char)m_binArray[m_currentIndex];

        if(m_currentIndex == 1)
            return;
        m_currentIndex++;
        do
        {
            if(m_currentIndex >= m_totalBytes)
                break;
            dataHeader = getDataHeader();
            m_currentIndex = m_currentIndex + 2;
            isFile = dataHeader.indexOf("filename") > 0;
            fieldName = getDataFieldValue(dataHeader, "name");
            if(isFile)
            {
                filePathName = getDataFieldValue(dataHeader, "filename");
                fileName = getFileName(filePathName);
                fileExt = getFileExt(fileName);
                contentType = getContentType(dataHeader);
                contentDisp = getContentDisp(dataHeader);
                typeMIME = getTypeMIME(contentType);
                subTypeMIME = getSubTypeMIME(contentType);
            }
            getDataSection();
            if(isFile && fileName.length() > 0)
            {
                if(m_deniedFilesList.contains(fileExt))
                    throw new SecurityException("The extension of the file is denied to be uploaded (1015).");
                if(!m_allowedFilesList.isEmpty() && !m_allowedFilesList.contains(fileExt))
                    throw new SecurityException("The extension of the file is not allowed to be uploaded (1010).");
                if(m_maxFileSize > (long)0 && (long)((m_endData - m_startData) + 1) > m_maxFileSize)
                    throw new SecurityException(String.valueOf((new StringBuffer("Size exceeded for this file : ")).append(fileName).append(" (1105).")));
                totalFileSize += (m_endData - m_startData) + 1;
                if(m_totalMaxFileSize > (long)0 && totalFileSize > m_totalMaxFileSize)
                    throw new SecurityException("Total File Size exceeded (1110).");
            }
            if(isFile)
            {
                com.jspsmart.upload.File newFile = new com.jspsmart.upload.File();
                
                newFile.setParent(this);
                newFile.setFieldName(fieldName);
                newFile.setFileName(fileName);
                newFile.setFileExt(fileExt);
                newFile.setFilePathName(filePathName);
                newFile.setIsMissing(filePathName.length() == 0);
                newFile.setContentType(contentType);
                newFile.setContentDisp(contentDisp);
                newFile.setTypeMIME(typeMIME);
                newFile.setSubTypeMIME(subTypeMIME);
                if(contentType.indexOf("application/x-macbinary") > 0)
                    m_startData = m_startData + 128;
                newFile.setSize((m_endData - m_startData) + 1);
                newFile.setStartData(m_startData);
                newFile.setEndData(m_endData);
                m_files.addFile(newFile);
            } else
            {
            	// 修改过            	
                String value = new String(m_binArray, m_startData, (m_endData - m_startData) + 1, "UTF-8");
                m_formRequest.putParameter(fieldName, value);
            }
            if((char)m_binArray[m_currentIndex + 1] == '-')
                break;
            m_currentIndex = m_currentIndex + 2;
        } while(true);
    }

    public int save(String destPathName)
        throws SmartUploadException, IOException, ServletException
    {
        return save(destPathName, 0);
    }

    public int save(String destPathName, int option)
        throws SmartUploadException, IOException, ServletException
    {
        int count = 0;
        if(destPathName == null)
            destPathName = m_application.getRealPath("/");
        if(destPathName.indexOf("/") != -1)
        {
            if(destPathName.charAt(destPathName.length() - 1) != '/')
                destPathName = String.valueOf(destPathName).concat("/");
        } else
        if(destPathName.charAt(destPathName.length() - 1) != '\\')
            destPathName = String.valueOf(destPathName).concat("\\");
        for(int i = 0; i < m_files.getCount(); i++)
            if(!m_files.getFile(i).isMissing())
            {
                m_files.getFile(i).saveAs(destPathName + m_files.getFile(i).getFileName(), option);
                count++;
            }

        return count;
    }

    public int getSize()
    {
        return m_totalBytes;
    }

    public byte getBinaryData(int index)
    {
        byte retval;
        try
        {
            retval = m_binArray[index];
        }
        catch(Exception e)
        {
            throw new ArrayIndexOutOfBoundsException("Index out of range (1005).");
        }
        return retval;
    }

    public Files getFiles()
    {
        return m_files;
    }

    public Request getRequest()
    {
        return m_formRequest;
    }

    public void downloadFile(String sourceFilePathName)
        throws SmartUploadException, IOException, ServletException
    {
        downloadFile(sourceFilePathName, null, null);
    }

    public void downloadFile(String sourceFilePathName, String contentType)
        throws SmartUploadException, IOException, ServletException
    {
        downloadFile(sourceFilePathName, contentType, null);
    }

    public void downloadFile(String sourceFilePathName, String contentType, String destFileName)
        throws SmartUploadException, IOException, ServletException
    {
        downloadFile(sourceFilePathName, contentType, destFileName, 65000);
    }

    public void downloadFile(String s, String s1, String s2, int i)
	throws ServletException, IOException, SmartUploadException
    {
		if(s == null)
		    throw new IllegalArgumentException("File '" + s +"' not found (1040).");
		if(s.equals(""))
		    throw new IllegalArgumentException("File '" + s +"' not found (1040).");
		if(!isVirtual(s) && m_denyPhysicalPath)
		    throw new SecurityException("Physical path is denied (1035).");
		if(isVirtual(s))
		    s = m_application.getRealPath(s);
		java.io.File file = new java.io.File(s);
		FileInputStream fileinputstream = new FileInputStream(file);
		long l = file.length();
		boolean flag = false;
		int k = 0;
		byte abyte0[] = new byte[i];
		if(s1 == null)
		    m_response.setContentType("application/x-msdownload");
		else
		if(s1.length() == 0)
		    m_response.setContentType("application/x-msdownload");
		else
		    m_response.setContentType(s1);
		m_response.setContentLength((int)l);
		m_contentDisposition = m_contentDisposition != null ?
		m_contentDisposition : "attachment;";
		if(s2 == null)
		    m_response.setHeader("Content-Disposition", 
		    m_contentDisposition + " filename=" + 
		    toUtf8String(getFileName(s)));
		else
		if(s2.length() == 0)
		    m_response.setHeader("Content-Disposition", 
		    m_contentDisposition);
		else
		    m_response.setHeader("Content-Disposition", 
		    m_contentDisposition + " filename=" + toUtf8String(s2));
		while((long)k < l)
		{
		    int j = fileinputstream.read(abyte0, 0, i);
		    k += j;
		    m_response.getOutputStream().write(abyte0, 0, j);
		}
		fileinputstream.close();
    }
   
    public static String toUtf8String(String s) {
	StringBuffer sb = new StringBuffer();
	for (int i=0;i<s.length();i++) {
	    char c = s.charAt(i);
	    if (c >= 0 && c <= 255) {
		sb.append(c);
	    } else {
		byte[] b;
		try {
		    b = Character.toString(c).getBytes("UTF-8");
		} catch (Exception ex) {
		    System.out.println(ex);
		    b = new byte[0];
		}
		for (int j = 0; j < b.length; j++) {
		    int k = b[j];
		    if (k < 0) k += 256;
		    sb.append("%" + Integer.toHexString(k).
		    toUpperCase());
		}
	    }
	}
	return sb.toString();
    }

    public void downloadField(ResultSet rs, String columnName, String contentType, String destFileName)
        throws SQLException, IOException, ServletException
    {
        if(rs == null)
            throw new IllegalArgumentException("The RecordSet cannot be null (1045).");
        if(columnName == null)
            throw new IllegalArgumentException("The columnName cannot be null (1050).");
        if(columnName.length() == 0)
            throw new IllegalArgumentException("The columnName cannot be empty (1055).");
        byte b[] = rs.getBytes(columnName);
        if(contentType == null)
            m_response.setContentType("application/x-msdownload");
        else
        if(contentType.length() == 0)
            m_response.setContentType("application/x-msdownload");
        else
            m_response.setContentType(contentType);
        m_response.setContentLength(b.length);
        if(destFileName == null)
            m_response.setHeader("Content-Disposition", "attachment;");
        else
        if(destFileName.length() == 0)
            m_response.setHeader("Content-Disposition", "attachment;");
        else
            m_response.setHeader("Content-Disposition", "attachment; filename=".concat(String.valueOf(destFileName)));
        m_response.getOutputStream().write(b, 0, b.length);
    }

    public void fieldToFile(ResultSet rs, String columnName, String destFilePathName)
        throws SQLException, SmartUploadException, IOException, ServletException
    {
        try
        {
            if(m_application.getRealPath(destFilePathName) != null)
                destFilePathName = m_application.getRealPath(destFilePathName);
            InputStream is_data = rs.getBinaryStream(columnName);
            FileOutputStream file = new FileOutputStream(destFilePathName);
            int c;
            while((c = is_data.read()) != -1) 
                file.write(c);
            file.close();
        }
        catch(Exception e)
        {
            throw new SmartUploadException("Unable to save file from the DataBase (1020).");
        }
    }

    private String getDataFieldValue(String dataHeader, String fieldName) throws UnsupportedEncodingException
    {
        String token = new String();
        String value = new String();
        int pos = 0;
        int i = 0;
        int start = 0;
        int end = 0;
        token =  String.valueOf((new StringBuffer(String.valueOf(fieldName))).append("=").append('"'));
        pos = dataHeader.indexOf(token);
        if(pos > 0)
        {
            i = pos + token.length();
            start = i;
            token = "\"";
            end = dataHeader.indexOf(token, i);
            if(start > 0 && end > 0)
                value = dataHeader.substring(start, end);
        }
        return value;
    }

    private String getFileExt(String fileName)
    {
        String value = new String();
        int start = 0;
        int end = 0;
        if(fileName == null)
            return null;
        start = fileName.lastIndexOf('.') + 1;
        end = fileName.length();
        value = fileName.substring(start, end);
        if(fileName.lastIndexOf('.') > 0)
            return value;
        else
            return "";
    }

    private String getContentType(String dataHeader)
    {
        String token = new String();
        String value = new String();
        int start = 0;
        int end = 0;
        token = "Content-Type:";
        start = dataHeader.indexOf(token) + token.length();
        if(start != -1)
        {
            end = dataHeader.length();
            value = dataHeader.substring(start, end);
        }
        return value;
    }

    private String getTypeMIME(String ContentType)
    {
        String value = new String();
        int pos = 0;
        pos = ContentType.indexOf("/");
        if(pos != -1)
            return ContentType.substring(1, pos);
        else
            return ContentType;
    }

    private String getSubTypeMIME(String ContentType)
    {
        String value = new String();
        int start = 0;
        int end = 0;
        start = ContentType.indexOf("/") + 1;
        if(start != -1)
        {
            end = ContentType.length();
            return ContentType.substring(start, end);
        } else
        {
            return ContentType;
        }
    }

    private String getContentDisp(String dataHeader)
    {
        String value = new String();
        int start = 0;
        int end = 0;
        start = dataHeader.indexOf(":") + 1;
        end = dataHeader.indexOf(";");
        value = dataHeader.substring(start, end);
        return value;
    }

    private void getDataSection()
    {
        boolean found = false;
        String dataHeader = new String();
        int searchPos = m_currentIndex;
        int keyPos = 0;
        int boundaryLen = m_boundary.length();
        m_startData = m_currentIndex;
        m_endData = 0;
        do
        {
            if(searchPos >= m_totalBytes)
                break;
            if(m_binArray[searchPos] == (byte)m_boundary.charAt(keyPos))
            {
                if(keyPos == boundaryLen - 1)
                {
                    m_endData = ((searchPos - boundaryLen) + 1) - 3;
                    break;
                }
                searchPos++;
                keyPos++;
            } else
            {
                searchPos++;
                keyPos = 0;
            }
        } while(true);
        m_currentIndex = m_endData + boundaryLen + 3;
    }

    private String getDataHeader()
    {
        int start = m_currentIndex;
        int end = 0;
        int len = 0;
        boolean found = false;
        while(!found) 
            if(m_binArray[m_currentIndex] == 13 && m_binArray[m_currentIndex + 2] == 13)
            {
                found = true;
                end = m_currentIndex - 1;
                m_currentIndex = m_currentIndex + 2;
            } else
            {
                m_currentIndex++;
            }
        String dataHeader = null;;
        try {
			dataHeader = new String(m_binArray, start, (end - start) + 1, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return dataHeader;
    }

    private String getFileName(String filePathName)
    {
        String token = new String();
        String value = new String();
        int pos = 0;
        int i = 0;
        int start = 0;
        int end = 0;
        pos = filePathName.lastIndexOf('/');
        if(pos != -1)
            return filePathName.substring(pos + 1, filePathName.length());
        pos = filePathName.lastIndexOf('\\');
        if(pos != -1)
            return filePathName.substring(pos + 1, filePathName.length());
        else
            return filePathName;
    }

    public void setDeniedFilesList(String deniedFilesList)
        throws SQLException, IOException, ServletException
    {
        String ext = "";
        if(deniedFilesList != null)
        {
            ext = "";
            for(int i = 0; i < deniedFilesList.length(); i++)
                if(deniedFilesList.charAt(i) == ',')
                {
                    if(!m_deniedFilesList.contains(ext))
                        m_deniedFilesList.addElement(ext);
                    ext = "";
                } else
                {
                    ext = ext + deniedFilesList.charAt(i);
                }

            if(ext != "")
                m_deniedFilesList.addElement(ext);
        } else
        {
            m_deniedFilesList = null;
        }
    }

    public void setAllowedFilesList(String allowedFilesList)
    {
        String ext = "";
        if(allowedFilesList != null)
        {
            ext = "";
            for(int i = 0; i < allowedFilesList.length(); i++)
                if(allowedFilesList.charAt(i) == ',')
                {
                    if(!m_allowedFilesList.contains(ext))
                        m_allowedFilesList.addElement(ext);
                    ext = "";
                } else
                {
                    ext = ext + allowedFilesList.charAt(i);
                }

            if(ext != "")
                m_allowedFilesList.addElement(ext);
        } else
        {
            m_allowedFilesList = null;
        }
    }

    public void setDenyPhysicalPath(boolean deny)
    {
        m_denyPhysicalPath = deny;
    }

    public void setForcePhysicalPath(boolean force)
    {
        m_forcePhysicalPath = force;
    }

    public void setContentDisposition(String contentDisposition)
    {
        m_contentDisposition = contentDisposition;
    }

    public void setTotalMaxFileSize(long totalMaxFileSize)
    {
        m_totalMaxFileSize = totalMaxFileSize;
    }

    public void setMaxFileSize(long maxFileSize)
    {
        m_maxFileSize = maxFileSize;
    }

    protected String getPhysicalPath(String filePathName, int option)
        throws IOException
    {
        String path = new String();
        String fileName = new String();
        String fileSeparator = new String();
        boolean isPhysical = false;
        fileSeparator = System.getProperty("file.separator");
        if(filePathName == null)
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        if(filePathName.equals(""))
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        if(filePathName.lastIndexOf("\\") >= 0)
        {
            path = filePathName.substring(0, filePathName.lastIndexOf("\\"));
            fileName = filePathName.substring(filePathName.lastIndexOf("\\") + 1);
        }
        if(filePathName.lastIndexOf("/") >= 0)
        {
            path = filePathName.substring(0, filePathName.lastIndexOf("/"));
            fileName = filePathName.substring(filePathName.lastIndexOf("/") + 1);
        }
        path = path.length() != 0 ? path : "/";
        File physicalPath = new File(path);
        if(physicalPath.exists())
            isPhysical = true;
        if(option == 0)
        {
            if(isVirtual(path))
            {
                path = m_application.getRealPath(path);
                if(path.endsWith(fileSeparator))
                    path = path + fileName;
                else
                    path = String.valueOf((new StringBuffer(String.valueOf(path))).append(fileSeparator).append(fileName));
                return path;
            }
            if(isPhysical)
            {
                if(m_denyPhysicalPath)
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                else
                    return filePathName;
            } else
            {
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
        }
        if(option == 1)
        {
            if(isVirtual(path))
            {
                path = m_application.getRealPath(path);
                if(path.endsWith(fileSeparator))
                    path = path + fileName;
                else
                    path = String.valueOf((new StringBuffer(String.valueOf(path))).append(fileSeparator).append(fileName));
                return path;
            }
            if(isPhysical)
                throw new IllegalArgumentException("The path is not a virtual path.");
            else
                throw new IllegalArgumentException("This path does not exist (1135).");
        }
        if(option == 2)
        {
            if(isPhysical)
                if(m_denyPhysicalPath)
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                else
                    return filePathName;
            if(isVirtual(path))
                throw new IllegalArgumentException("The path is not a physical path.");
            else
                throw new IllegalArgumentException("This path does not exist (1135).");
        } else
        {
            return null;
        }
    }

    public void uploadInFile(String destFilePathName)
        throws SmartUploadException, IOException
    {
        int intsize = 0;
        int pos = 0;
        int readBytes = 0;
        if(destFilePathName == null)
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        if(destFilePathName.length() == 0)
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        if(!isVirtual(destFilePathName) && m_denyPhysicalPath)
            throw new SecurityException("Physical path is denied (1035).");
        intsize = m_request.getContentLength();
        m_binArray = new byte[intsize];
        for(; pos < intsize; pos += readBytes)
            try
            {
                readBytes = m_request.getInputStream().read(m_binArray, pos, intsize - pos);
            }
            catch(Exception e)
            {
                throw new SmartUploadException("Unable to upload.");
            }

        if(isVirtual(destFilePathName))
            destFilePathName = m_application.getRealPath(destFilePathName);
        try
        {
            File file = new File(destFilePathName);
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(m_binArray);
            fileOut.close();
        }
        catch(Exception e)
        {
            throw new SmartUploadException("The Form cannot be saved in the specified file (1030).");
        }
    }

    private boolean isVirtual(String pathName)
    {
        if(m_application.getRealPath(pathName) != null)
        {
            File virtualFile = new File(m_application.getRealPath(pathName));
            return virtualFile.exists();
        } else
        {
            return false;
        }
    }

    protected byte m_binArray[];
    protected HttpServletRequest m_request;
    protected HttpServletResponse m_response;
    protected ServletContext m_application;
    private int m_totalBytes;
    private int m_currentIndex;
    private int m_startData;
    private int m_endData;
    private String m_boundary;
    private long m_totalMaxFileSize;
    private long m_maxFileSize;
    private Vector m_deniedFilesList;
    private Vector m_allowedFilesList;
    private boolean m_denyPhysicalPath;
    private boolean m_forcePhysicalPath;
    private String m_contentDisposition;
    public static final int SAVE_AUTO = 0;
    public static final int SAVE_VIRTUAL = 1;
    public static final int SAVE_PHYSICAL = 2;
    private Files m_files;
    private Request m_formRequest;
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\Program Files\Programme\JAVA\MyEclipse Enterprise Workbench 5.1.0 GA\workspace\FpwlFaces\WebRoot\WEB-INF\lib\jspSmartUpload.jar
	Total time: 47 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/