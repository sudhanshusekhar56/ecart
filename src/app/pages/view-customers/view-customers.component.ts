import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { CustomerService } from '../../service/customer-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-view-customers',
  imports: [CommonModule],
  templateUrl: './view-customers.component.html',
  styleUrl: './view-customers.component.css',
})
export class ViewCustomersComponent implements OnInit {
  customers: Customer[] = [];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe({
      next: (data: Customer[]) => {
        this.customers = data;
      },
      error: (error: any) => {
        console.error('Error fetching customers:', error);
      },
    });
  }
}
