package org.firstinspires.ftc.discoduckbots.util;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TensorFlowSkystoneFinder {

    private static final String SKYSTONE_LABEL = "Skystone";

    /**
     * On Red Side need to evaluate blocks from Right to Left (i.e. left value descending)
     */
    private class SortRecognitionsByLeftValueRedSide implements Comparator<Recognition>{
        @Override
        public int compare(Recognition r1, Recognition r2) {
            Float r1Left = r1.getLeft();
            Float r2Left = r2.getLeft();
            return r2Left.compareTo(r1Left);
        }
    }

    /**
     * On Blue Side need to evaluate blocks from Left to Right (i.e. left value ascending)
     */
    private class SortRecognitionsByLeftValueBlueSide implements Comparator<Recognition>{
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
     * @param recognitionList - A List of TensorFlow Recognitions
     * @param isRedSide - a boolean value helping to know which side of field we
     *                  are on
     * @return - 1 - if the dice rolled was either 1 or 4
     *           2 - if the dice rolled was either 2 or 5
     *           3 - if the dice rolled was either 3 or 6
     *        null - if there is not enough recognitions to determine the roll
     */
    public Integer getSkystoneDiceRoll(List<Recognition> recognitionList, boolean isRedSide){

        /* If No Recognitions or One Non-Skystone Recognition - we don't have enough data  */
        if (recognitionList == null || recognitionList.size() == 0 ||
                (recognitionList.size() == 1 && !SKYSTONE_LABEL.equals(recognitionList.get(0).getLabel()))){
            return null;
        }

        /* Sort List of Recognitions Depending on which side we are looking at it from */
        if (isRedSide){
            Collections.sort(recognitionList, new SortRecognitionsByLeftValueRedSide());
        }
        else{
            Collections.sort(recognitionList, new SortRecognitionsByLeftValueBlueSide());
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