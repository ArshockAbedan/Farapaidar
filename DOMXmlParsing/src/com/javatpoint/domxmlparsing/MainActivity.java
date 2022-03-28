package com.javatpoint.domxmlparsing;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
TextView tv1;

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
tv1=(TextView)findViewById(R.id.textView1);
try {
InputStream is = getAssets().open("file.xml");

DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

Document doc = dBuilder.parse(is);
Element element=doc.getDocumentElement();
element.normalize();

NodeList nList = doc.getElementsByTagName("employee");
for (int i=0; i<nList.getLength(); i++) {

Node node = nList.item(i);
if (node.getNodeType() == Node.ELEMENT_NODE) {
Element element2 = (Element) node;
tv1.setText(tv1.getText()+"\n\nName : " + getValue("name", element2)+"\n");
tv1.setText(tv1.getText()+"Salary : " + getValue("salary", element2)+"\n");
tv1.setText(tv1.getText()+"-----------------------");
}
}
} catch (Exception e) {e.printStackTrace();}

}
private static String getValue(String tag, Element element) {
NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
Node node = (Node) nodeList.item(0);
return node.getNodeValue();
}

}
