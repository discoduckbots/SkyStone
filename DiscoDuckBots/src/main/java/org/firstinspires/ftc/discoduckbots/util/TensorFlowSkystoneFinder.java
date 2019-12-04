package org.firstinspires.ftc.discoduckbots.util;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TensorFlowSkystoneFinder {

    private static final String SKYSTONE_LABEL = "Skystone";

    /**
     * Sort by Left Value in Ascending Order
     */
    private class SortRecognitionsByLeftValue implements Comparator<Recognition>{
        @Override
        public int compare(Recognition r1, Recognition r2) {
            Float r1Left = r1.getLeft();
            Float r2Left = r2.getLeft();
            return r1Left.compareTo(r2Left);
        }
    }

    /**
     * Method takes a list of tensor flow recognitions and returns the dice roll.
     * Can be used in autonomous to determine which stones are skystones.
     *
     *
     * @param telemetry
     * @param recognitionList - A List of TensorFlow Recognitions
     * @param isRedSide - a boolean value helping to know which side of field we
     *                  are on
     * @return - 1 - if the dice rolled was either 1 or 4
     *           2 - if the dice rolled was either 2 or 5
     *           3 - if the dice rolled was either 3 or 6
     *        null - if there is not enough recognitions to determine the roll
     */
    public Integer getSkystoneDiceRoll(Telemetry telemetry, List<Recognition> recognitionList, boolean isRedSide){

        /* If No Recognitions or One Non-Skystone Recognition - we don't have enough data  */
        if (recognitionList == null || recognitionList.size() == 0 ||
                (recognitionList.size() == 1 && !SKYSTONE_LABEL.equals(recognitionList.get(0).getLabel()))){
            return null;
        }

        if (isRedSide){
            /* Reverse the Order if looking from Red Side as we want to evaluate Right Most Blocks first */
            Collections.sort(recognitionList, Collections.reverseOrder(new SortRecognitionsByLeftValue()));
        }
        else{
            Collections.sort(recognitionList, new SortRecognitionsByLeftValue());
        }

        /* Evaluate Recognitions from Outside in to see which is the Skystone */
        for (int i=0; i<recognitionList.size(); i++){
            Recognition recognition = recognitionList.get(i);

            if (i==0 && SKYSTONE_LABEL.equals(recognition.getLabel())){
                return 3;
            }

            if (i==1 && SKYSTONE_LABEL.equals(recognition.getLabel())){
                return 2;
            }
        }

        return 1;
    }
}