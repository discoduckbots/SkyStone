package org.firstinspires.ftc.discoduckbots.util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TensorFlowSkystoneFinderTest {

    private class TestRecognition implements Recognition{

        private String label;
        private float left;

        public TestRecognition(String label, float left){
            this.label = label;
            this.left = left;
        }

        @Override
        public String getLabel() {
            return label;
        }

        @Override
        public float getConfidence() {
            return 0;
        }

        @Override
        public float getLeft() {
            return left;
        }

        @Override
        public float getRight() {
            return 0;
        }

        @Override
        public float getTop() {
            return 0;
        }

        @Override
        public float getBottom() {
            return 0;
        }

        @Override
        public float getWidth() {
            return 0;
        }

        @Override
        public float getHeight() {
            return 0;
        }

        @Override
        public int getImageWidth() {
            return 0;
        }

        @Override
        public int getImageHeight() {
            return 0;
        }

        @Override
        public double estimateAngleToObject(AngleUnit angleUnit) {
            return 0;
        }
    }

    @Test
    public void getSkystoneDiceRoll_whenNoRecognitionsPassed_ShouldReturnNull(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();
        Integer result = finder.getSkystoneDiceRoll(null, true);
        assertNull(result);
    }

    @Test
    public void getSkystoneDiceRoll_whenOneRecognitionNotASkystone_ShouldReturnNull(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();
        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Integer result = finder.getSkystoneDiceRoll(recognitions, true);
        assertNull(result);
    }

    @Test
    public void getSkystoneDiceRoll_whenOneRecognitionIsASkystone_ShouldReturn3(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();
        Recognition recognition = new TestRecognition("Skystone", 0);
        recognitions.add(recognition);

        Integer result = finder.getSkystoneDiceRoll(recognitions, true);
        assertEquals(3, result.intValue());
    }

    @Test
    public void getSkystoneDiceRoll_whenOneRecognitionIsNotASkystone_ShouldReturnNull(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();
        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Integer result = finder.getSkystoneDiceRoll(recognitions, true);
        assertNull(result);
    }

    @Test
    public void getSkystoneDiceRoll_whenTwoRecognitionsNeitherSkystone_ShouldReturn1(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();

        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Recognition recognition1 = new TestRecognition("Stone", 10);
        recognitions.add(recognition1);

        Integer result = finder.getSkystoneDiceRoll(recognitions, true);
        assertEquals(1, result.intValue());
    }

    @Test
    public void getSkystoneDiceRoll_whenTwoRecognitionsRightmostOneIsSkystoneOnRedSide_ShouldReturn3(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();

        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Recognition recognition1 = new TestRecognition("Skystone", 10);
        recognitions.add(recognition1);

        Integer result = finder.getSkystoneDiceRoll(recognitions, true);
        assertEquals(3, result.intValue());
    }

    @Test
    public void getSkystoneDiceRoll_whenTwoRecognitionsLeftmostOneIsSkystoneOnRedSide_ShouldReturn2(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();

        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Recognition recognition1 = new TestRecognition("Skystone", -10);
        recognitions.add(recognition1);

        Integer result = finder.getSkystoneDiceRoll(recognitions, true);
        assertEquals(2, result.intValue());
    }

    @Test
    public void getSkystoneDiceRoll_whenTwoRecognitionsRightmostOneIsSkystoneOnBlueSide_ShouldReturn2(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();

        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Recognition recognition1 = new TestRecognition("Skystone", 10);
        recognitions.add(recognition1);

        Integer result = finder.getSkystoneDiceRoll(recognitions, false);
        assertEquals(2, result.intValue());
    }

    @Test
    public void getSkystoneDiceRoll_whenTwoRecognitionsLeftmostOneIsSkystoneOnBlueSide_ShouldReturn3(){
        TensorFlowSkystoneFinder finder = new TensorFlowSkystoneFinder();

        List<Recognition> recognitions = new ArrayList<>();

        Recognition recognition = new TestRecognition("Stone", 0);
        recognitions.add(recognition);

        Recognition recognition1 = new TestRecognition("Skystone", -10);
        recognitions.add(recognition1);

        Integer result = finder.getSkystoneDiceRoll(recognitions, false);
        assertEquals(3, result.intValue());
    }
}