/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   Request.java

package com.jspsmart.upload;

import java.util.Enumeration;
import java.util.Hashtable;

public class Request
{

    Request()
    {
        m_parameters = new Hashtable();
        m_counter = 0;
    }

    protected void putParameter(String name, String value)
    {
        if(name == null)
            throw new IllegalArgumentException("The name of an element cannot be null.");
        if(m_parameters.containsKey(name))
        {
            Hashtable values = (Hashtable)m_parameters.get(name);
            values.put(new Integer(values.size()), value);
        } else
        {
            Hashtable values = new Hashtable();
            values.put(new Integer(0), value);
            m_parameters.put(name, values);
            m_counter++;
        }
    }

    public String getParameter(String name)
    {
        if(name == null)
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        Hashtable values = (Hashtable)m_parameters.get(name);
        if(values == null)
            return null;
        else
            return (String)values.get(new Integer(0));
    }

    public Enumeration getParameterNames()
    {
        return m_parameters.keys();
    }

    public String[] getParameterValues(String name)
    {
        if(name == null)
            throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
        Hashtable values = (Hashtable)m_parameters.get(name);
        if(values == null)
            return null;
        String strValues[] = new String[values.size()];
        for(int i = 0; i < values.size(); i++)
            strValues[i] = (String)values.get(new Integer(i));

        return strValues;
    }

    private Hashtable m_parameters;
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