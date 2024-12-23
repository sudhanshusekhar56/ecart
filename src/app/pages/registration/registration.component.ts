import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from "@angular/forms";
import { AuthService } from "../../service/auth-service.service";
import { Router } from "@angular/router"; // Import Router
import { Customer } from "../../model/customer.model";

@Component({
  selector: "app-registration",
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: "./registration.component.html",
  styleUrls: ["./registration.component.css"], // Fix styleUrl to styleUrls
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  errorMessage: string = "";

  successMessage: string = "";
  registeredCustomer: Customer | null = null;
  showModal: boolean = false;

  showSuccessModal(): void {
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthService, // Inject the service
    private router: Router // Inject Router for navigation
  ) {
    this.registrationForm = this.fb.group({
      customerName: ["", [Validators.required, Validators.minLength(3)]],
      customerEmail: ["", [Validators.required, Validators.email]],
      customerAddress: ["", [Validators.required]],
      customerPassword: ["", [Validators.required, Validators.minLength(10)]],
      confirmPassword: ["", [Validators.required]],
    });
  }

  get f(): FormGroup["controls"] {
    return this.registrationForm.controls;
  }

  submit(): void {
    if (this.registrationForm.invalid) {
      return;
    }

    const formData = this.registrationForm.value;

    // Check if passwords match
    if (formData.customerPassword !== formData.confirmPassword) {
      this.errorMessage = "Passwords do not match!";
      return;
    }

    this.errorMessage = "";

    // Create customer object for registration
    const customer: Customer = {
      customerName: formData.customerName,
      customerEmail: formData.customerEmail,
      customerAddress: formData.customerAddress,
      customerPassword: formData.customerPassword,
      customerId: 0,
      isAdmin: false,
    };

    this.authService.register(customer).subscribe({
      next: (response) => {
        this.showSuccessModal();
        this.registeredCustomer = response;
        this.successMessage = "Registration successful!";
      },
      error: (err) => {
        // Handle errors
        this.errorMessage =
          err?.error?.message || "Registration failed. Please try again.";
      },
    });
  }

  goLogin() {
    this.router.navigate(["/login"]);
  }
}
