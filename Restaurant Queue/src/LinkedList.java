//Description: creates a linklist class by hand in order
//to get capibilities that might not be in java apa
//link list and helps it fit the program

public class LinkedList {
    Table first;
    int size;
    public LinkedList(){
        first = null;
        size = 0;
    }

    public void add(int numberOfGuests, String name){
        Table newGuest = new Table(numberOfGuests, name);
        if(first == null){
            first = newGuest;
        }
        else{
            Table pointer = first;
            while(pointer.next != null)
                pointer = pointer.next;

            pointer.next = newGuest;
        }
        size++;
    }
    
    public Table removeFirst() {//removes the first Table
    	Table empt;
    	empt = new Table.EmptyTable();
    	if(first == null) {
    		return empt;
    	}
    	else {
    		Table pos = first;
    		first = first.next;
    		size--;
    		return pos;
    	}
    }
    
    public Table removeLast() {//removes the last
    	Table empt;
    	empt = new Table.EmptyTable();
    	if (first != null)
    	{
    		int n = 0;
    		if (n == size) // it is the first value
    		{
    			first = first.next;
    			size--;
    		}else{
    			Table previous = first;
    			Table current = first.next;
    			while (current != null && n != size-2)
    			{
    				previous = current;
    				current = current.next;
    				n++;
    			}
    			
    			previous.next = null;
    			size--;
    			return current;
    		}
    	}
    	else {
        	return empt;
    	}
    	return empt;
    }
    
    public Table removeMiddle(String name) {// remove middle 
    		int n = 0;
    		Table empt;
        	empt = new Table.EmptyTable();
    		if(first!= null) {
    			Table previous = first;
				Table current = first.next;
				while (n != getPosition(name)-1)
				{
					previous = current;
					current = current.next;
					n++;
				} 
				previous.next=current.next;
				size--;
				return current;
    		}
    		else {
    			return empt;
    		}
		
    }
    
    public Table removeGuest(String name) {//remove value
    	Table empt;
    	empt = new Table.EmptyTable();
    	if(first== null) {
    		return empt;
    	}
    	else if(first.name.trim().equals(name)) {
    		Table pos = first;
    		first = first.next;
    		return pos;
    	}
    	else {
    		if(getPosition(name)==-1){
    			return empt;
    		}
    		else if(getPosition(name)+1==size) {
    			Table j = removeLast();
    			return j;
    		}
    		else {
    			Table z = removeMiddle(name);
    			return z;
    		}
    	}
    }
    
    public int getPosition(String name) {//gets position
    	if(first!= null) {
    		int i = 0;
    		if(first.name.trim().equals(name)){
    			return 0;
    		}
    		else {
    			Table current = first;
    			while (!current.name.trim().equals(name))
    			{
    				current = current.next;
    				i++;
    				if(current == null) {
    					return -1;
    				}
    			}
    			return i;
    		}
    	}
    	else{
    		return -1;
    	}
    }
    
    public int getNumberOfParties(int size) {
    	int num = 0;
    	if(first == null) {
    		return num;
    	}
    	else {
    		Table pos = first;
    		while (pos != null){
    			if(pos.guests == size)
    				num++;
    			pos = pos.next;
    		}
    		return num;
    	}
    }
    
    public String listReservations() {// gets a string of all the values
    	if(first == null) {
    		return "No reservations in queue at this time.\n";
    	}
    	else {
    		int i = 0;
    		Table pos = first;
    		String j = "";
    		while (pos != null){
    			j += pos.toString();
    			pos = pos.next;
    			i++;
    		}
    		j+= "\nTotal reservations: "+i + ".\n";
    		return j;
    	}
    }
}
