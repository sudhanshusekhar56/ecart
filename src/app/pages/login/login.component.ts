import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Customer } from '../../model/customer.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response: Customer) => {
        localStorage.setItem('customer', JSON.stringify(response));
        if (response.isAdmin == true) {
          this.router.navigate(['/admin-dashboard']);
        } else {
          this.router.navigate(['/']);
        }
      },
      error: (error: any) => {
        this.errorMessage = 'Invalid username or password.';
        console.error('Login error:', error);
      },
    });
  }
}
