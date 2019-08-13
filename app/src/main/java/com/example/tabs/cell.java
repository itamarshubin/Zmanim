package com.example.tabs;

public class cell
{
    private String title;
    private String time;

    public cell(String title, String time)
    {
        this.title = title;
        this.time = time;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
