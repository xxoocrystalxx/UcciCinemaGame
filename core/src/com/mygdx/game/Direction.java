package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public enum Direction {

    UP(Axis.Y, AxisDirection.POSITIVE),
    RIGHT(Axis.X, AxisDirection.POSITIVE),
    DOWN(Axis.Y, AxisDirection.NEGATIVE),
    LEFT(Axis.X, AxisDirection.NEGATIVE);

    private final Axis axis;
    private final AxisDirection axisDirection;



    private final Vector2 data2d;



    Direction(Axis axis, AxisDirection axisDirection){
        this.axis = axis;
        this.axisDirection = axisDirection;
        this.data2d = new Vector2(
                axis == Axis.X ? axisDirection.getValue() : 0,
                axis == Axis.Y ? axisDirection.getValue() : 0
        );
    }

    public static Direction getRandom(){
        return values()[MathUtils.random(3)];
    }

    public Vector2 getData2d() {
        return data2d;
    }

    public Axis getAxis() {
        return axis;
    }

    public AxisDirection getAxisDirection() {
        return axisDirection;
    }

    public enum Axis {
        X,
        Y;
    }
    public enum AxisDirection {
        NEGATIVE(-1),
        POSITIVE(1);

        private final int value;

        AxisDirection(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }


    }
}
