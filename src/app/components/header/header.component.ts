import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  userName: string | null = null;
  isAdmin: boolean | null = null;

  constructor(private router: Router) {}

  ngOnInit(): void {
    const customer = localStorage.getItem('customer');
    if (customer) {
      const customerObj = JSON.parse(customer);
      this.userName = customerObj.customerName;
      this.isAdmin = customerObj.isAdmin;
    }
  }

  goHome() {
    if (this.isAdmin) {
      this.router.navigate(['/admin-dashboard']);
    } else {
      this.router.navigate(['']);
    }
  }

  logout(): void {
    localStorage.removeItem('customer');
    this.userName = null;
    this.router.navigate(['/login']);
  }
}
