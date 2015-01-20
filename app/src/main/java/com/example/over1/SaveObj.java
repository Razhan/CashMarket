package com.example.over1;

/**
 * Created by razha_000 on 1/11/2015.
 */

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveObj implements Serializable{
    public XYMultipleSeriesRenderer XYMultipleSeriesRenderer;
    public XYMultipleSeriesDataset Dataset;
    public XYSeries CurrentSeries;
    public XYSeriesRenderer CurrentRenderer;
    public double X;
    public double Y;
    //public ArrayList<String> bottoms = new ArrayList<String>();
    public ArrayList<String> nodes = new ArrayList<String>();


    public SaveObj(XYMultipleSeriesRenderer renders, XYMultipleSeriesDataset dataset, XYSeriesRenderer render,
                   XYSeries series, double x, double y, ArrayList<String> inodes ) {
        XYMultipleSeriesRenderer = renders;
        Dataset = dataset;
        CurrentSeries = series;
        CurrentRenderer = render;
        X = x;
        Y = y;
        //bottoms = ibottoms;
        nodes = inodes;
    }

    public SaveObj(XYMultipleSeriesRenderer renders, XYMultipleSeriesDataset dataset) {
        XYMultipleSeriesRenderer = renders;
        Dataset = dataset;
    }
}
