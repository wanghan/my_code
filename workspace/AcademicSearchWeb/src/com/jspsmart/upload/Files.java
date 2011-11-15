/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   Files.java

package com.jspsmart.upload;

import java.io.IOException;
import java.util.*;

// Referenced classes of package com.jspsmart.upload:
//            File, SmartUpload

public class Files
{

    Files()
    {
        m_files = new Hashtable();
        m_counter = 0;
    }

    protected void addFile(File newFile)
    {
        if(newFile == null)
        {
            throw new IllegalArgumentException("newFile cannot be null.");
        } else
        {
            m_files.put(new Integer(m_counter), newFile);
            m_counter++;
            return;
        }
    }

    public File getFile(int index)
    {
        if(index < 0)
            throw new IllegalArgumentException("File's index cannot be a negative value (1210).");
        File retval = (File)m_files.get(new Integer(index));
        if(retval == null)
            throw new IllegalArgumentException("Files' name is invalid or does not exist (1205).");
        else
            return retval;
    }

    public int getCount()
    {
        return m_counter;
    }

    public long getSize()
        throws IOException
    {
        long tmp = 0L;
        for(int i = 0; i < m_counter; i++)
            tmp += getFile(i).getSize();

        return tmp;
    }

    public Collection getCollection()
    {
        return m_files.values();
    }

    public Enumeration getEnumeration()
    {
        return m_files.elements();
    }

    private SmartUpload m_parent;
    private Hashtable m_files;
    private int m_counter;
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\Program Files\Programme\JAVA\MyEclipse Enterprise Workbench 5.1.0 GA\workspace\FpwlFaces\WebRoot\WEB-INF\lib\jspSmartUpload.jar
	Total time: 16 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/