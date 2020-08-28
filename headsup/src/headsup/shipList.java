package headsup;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class shipList implements ListModel  {
	private ArrayList<String> ships = new ArrayList<String>();
	public shipList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getElementAt(int index) {
		return ships.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return ships.size();
	}
	public void remove (int index) {
		if (index<ships.size())
		ships.remove(index);
	}
	public void remove (String element) {
		if (ships.contains(element))
		ships.remove(element);
	}
	public void add(String element) {
		ships.add(element);
	}
	public void empty () {
		ships = new ArrayList<String>();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

}
