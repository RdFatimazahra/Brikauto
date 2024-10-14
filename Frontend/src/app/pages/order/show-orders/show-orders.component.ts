import { Component, OnInit } from '@angular/core';
import { OrderConfirmation } from 'src/app/interfaces/OrderConfirmation';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-show-orders',
  templateUrl: './show-orders.component.html',
  styleUrls: ['./show-orders.component.scss']
})
export class ShowOrdersComponent implements OnInit {
  orders: OrderConfirmation[] = [];
  loading: boolean = true;
  error: string | null = null;
  page: number = 1;  // Add this line for pagination

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.loading = true;
    this.error = null;
    this.orderService.getAllOrders().subscribe(
      (orders) => {
        this.orders = orders;
        this.loading = false;
      },
      (error) => {
        console.error('Error fetching orders', error);
        this.error = 'Failed to load orders. Please try again later.';
        this.loading = false;
      }
    );
  }
}
