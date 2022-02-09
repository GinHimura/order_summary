package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Date moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> orderItem = new ArrayList<>();
	
	public Order() {
		
	}

	public Order(OrderStatus status, Client client) {
		this.moment = new Date();
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void addItem(OrderItem orderItem) {
		this.orderItem.add(orderItem);
	}
	
	public void removeItem(OrderItem orderItem) {
		this.orderItem.remove(orderItem);
	}
	
	public double total() {
		double sum = 0.0;
		for (OrderItem item : orderItem) {
			sum += item.subTotal();
		}
		return sum;
	}

	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
		 sb.append("\nOrder moment: "); 
		 sb.append(sdf.format(moment));
		 sb.append("\nOrder statud: "); 
		 sb.append(status);
		 sb.append("\nClient: "); 
		 sb.append(client.getName() + " (" );
		 String time = sdf.format(client.getBirthDate());
		 sb.append(time.substring(0,10) + ") - ");
		 sb.append(client.getEmail());
		 sb.append("\nOrder items:");
		 for(OrderItem oI : orderItem) {
			 sb.append("\n" + oI.getProduct().getName() + ", $");
			 sb.append(String.format("%.2f", oI.getProduct().getPrice())); 
			 sb.append(", Quantity: ");
			 sb.append(oI.getQuantity());
			 sb.append(", Subtotal: $");
			 sb.append(String.format("%.2f", oI.subTotal()));
		 }
		 sb.append("\nTotal price : $");
		 sb.append(String.format("%.2f", total()));
		return sb.toString();
	}
	
	
	
}
