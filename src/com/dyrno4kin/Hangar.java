package com.dyrno4kin;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
class Hangar<T extends IAir> {
    private HashMap<Integer,T> _places;
    private int _maxCount;
    private int PictureWidth;
    private int PictureHeight;
    public int getPictureWidth(){
        return PictureWidth;
    }

    public void setPictureWidth(int pw){
        PictureWidth = pw;
    }

    public int getPictureHeight(){
        return PictureHeight;
    }

    public void setPictureHeight(int ph){
        PictureHeight = ph;
    }
    private int _placeSizeWidth = 210;
    private int _placeSizeHeight = 80;

    public Hangar( int sizes, int pictureWidth, int pictureHeight)
    {
        _maxCount = sizes;
        _places = new  HashMap<Integer,T>();
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
    }

    public int Plus(T air)throws HangarOverflowException
    {
        if (_places.size() == _maxCount)
        {
            throw new HangarOverflowException();
        }
        for (int i = 0; i < _maxCount; i++)
        {
            if (CheckFreePlace(i))
            {
                _places.put(i, air);
                _places.get(i).SetPosition(10 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 35, PictureWidth, PictureHeight);
                return i;
            }
        }
        return -1;
    }

    public  T Minus( int index)throws HangarNotFoundException
    {
        if (index < 0 || index > _maxCount)
        {
            return null;
        }
        if (!CheckFreePlace(index))
        {
            T air = _places.get(index);
            _places.remove(index);
            return air;
        }
        throw new HangarNotFoundException(index);
    }

    private boolean CheckFreePlace(int index)
    {
        return !_places.containsKey(index);
    }
    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.keySet().toArray().length; i++)
        {
            _places.get(_places.keySet().toArray()[i]).DrawAir(g);
        }
    }
    private void DrawMarking(Graphics g)
    {
        Color newColor3 = new Color(0, 0, 0);
        g.setColor(newColor3);
        g.drawRect(0, 0, (_places.size() / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < _maxCount / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
                g.drawRect(i * _placeSizeWidth, j * _placeSizeHeight, 160, 5 );
                Color nc = new Color(0,0,0);
                g.setColor(nc);
                g.fillRect(i * _placeSizeWidth, j * _placeSizeHeight, 160, 5);
            }
            g.setColor(newColor3);
            g.drawLine( i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }

    public T getAir(int index) throws  HangarNotFoundException{
        if (_places.get(index) != null) {
            return _places.get(index);
        }  throw new HangarNotFoundException(index);
    }
    public void setAir(int index, T ship) throws HangarOccupiedPlaceException{
        if(CheckFreePlace(index)) {
            _places.put(index, ship);
            _places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 35, PictureWidth, PictureHeight);
        }
        else{
            throw new HangarOccupiedPlaceException(index);
        }
    }
}
