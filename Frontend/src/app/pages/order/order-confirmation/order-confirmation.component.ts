import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderConfirmation } from 'src/app/interfaces/OrderConfirmation';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-confirmation',
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.scss']
})
export class OrderConfirmationComponent implements OnInit {
  orderDetails: OrderConfirmation[] = [];
  isLoading: boolean = true;
  error: string | null = null;

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const orderId = +params['id']; // Convert to number
      console.log("the order id is ",orderId)
      if (orderId) {
        this.loadOrderDetails(orderId);
      } else {
        this.error = 'Invalid order ID';
        this.isLoading = false;
      }
    });
  }

  loadOrderDetails(orderId: number): void {
    this.isLoading = true;
    this.orderService.getOrderConfirmation(orderId).subscribe(
      (order) => {
        this.orderDetails = order;
        this.isLoading = false;
      },
      (error) => {
        console.error('Error fetching order details:', error);
        this.error = 'Failed to load order details. Please try again later.';
        this.isLoading = false;
      }
    );
  }

  get totalPrice(): number {
    return this.orderDetails.reduce((total, item) => total + (item.piecePrice * item.quantity), 0);
  }

  get orderDate(): string {
    return this.orderDetails.length > 0 ? this.orderDetails[0].orderDate : '';
  }

  get orderId(): number {
    return this.orderDetails.length > 0 ? this.orderDetails[0].orderId : 0;
  }

  get orderStatus(): string {
    return this.orderDetails.length > 0 ? this.orderDetails[0].orderStatus : '';
  }
}