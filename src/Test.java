import source.Control;
import source.Document;
import source.Tag;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * Created by avabe on 08.07.2017.
 */
public class Test {

    public static void main(String[] args) throws IOException {

        Tag testTag1 = new Tag("Urlaub2017");
        Tag testTag2 = new Tag("Urlaub2016");
        Tag testTag3 = new Tag("Sommer2017");
        Tag testTag4 = new Tag("Urlaub");
        Document testA = new Document();
        Document testB = new Document();
        Document testC = new Document();
        Document testD = new Document();
        Document testE = new Document();

        testTag1.getAssignedDocList().add(testA);
        testTag1.getAssignedDocList().add(testB);
        testTag1.getAssignedDocList().add(testC);
        testTag1.getAssignedDocList().add(testD);
        testTag1.getAssignedDocList().add(testE);

        testA.setDocName("TestA");
        testA.setDocPath("C:\\Users\\avabe\\Desktop\\antrag.pdf");
        testB.setDocName("TestB");
        testC.setDocName("TestC");
        testD.setDocName("TestD");
        testE.setDocName("TestE");

        Control.getInstance().getDocList().add(testA);
        Control.getInstance().getDocList().add(testB);
        Control.getInstance().getDocList().add(testC);
        Control.getInstance().getDocList().add(testD);
        Control.getInstance().getDocList().add(testE);
        Control.getInstance().searchDoc("A");

        Control.getInstance().getTagSet().add(testTag1);
        Control.getInstance().getTagSet().add(testTag2);
        Control.getInstance().getTagSet().add(testTag3);
        Control.getInstance().getTagSet().add(testTag4);
        System.out.println();

        Control.getInstance().searchDoc("e");

        System.out.println();

        Control.getInstance().outputContainedDoc(testTag1) ;


        Scanner s = new Scanner(System.in);

        Control.getInstance().getDocList().get(0).setDocPath(s.next());

        Control.getInstance().getDocList().get(0).openDoc();


    }
}
