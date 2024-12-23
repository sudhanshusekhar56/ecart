import { CommonModule } from "@angular/common";
import { Component, OnInit, ViewChild } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { Customer } from "../../model/customer.model";
import { CustomerService } from "../../service/customer-service.service";
import { GoBackComponent } from "../../components/go-back/go-back.component";
import { PopupComponent } from "../../components/popup/popup.component";

@Component({
  selector: "app-profile",
  imports: [CommonModule, FormsModule, GoBackComponent, PopupComponent],
  templateUrl: "./profile.component.html",
  styleUrl: "./profile.component.css",
})
export class ProfileComponent implements OnInit {
  customer!: Customer;

  isPasswordEditMode: boolean = false;
  isEditMode: boolean = false;
  oldPassword: string = "";
  newPassword: string = "";
  confirmPassword: string = "";

  @ViewChild("popup") popup!: PopupComponent;

  constructor(
    private router: Router,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    if (localStorage != undefined) {
      const customerData = localStorage.getItem("customer");
      if (customerData) {
        this.customer = JSON.parse(customerData);
      } else {
        this.router.navigate(["/login"]);
      }
    }
  }
  toggleEditMode() {
    if (this.isPasswordEditMode) {
      this.togglePasswordEditMode();
    }
    this.isEditMode = !this.isEditMode;
  }

  togglePasswordEditMode() {
    if (this.isEditMode) {
      this.toggleEditMode();
    }
    this.isPasswordEditMode = !this.isPasswordEditMode;
  }

  changePassword() {
    if (this.newPassword !== this.confirmPassword) {
      this.popup.showPopup("Passwords do not match!");
      return;
    }

    this.customerService
      .changePassword(
        this.customer.customerId,
        this.oldPassword,
        this.newPassword
      )
      .subscribe({
        next: () => {
          this.popup.showPopup("Password updated successfully!");
          this.isPasswordEditMode = false;
        },
        error: (error) => {
          console.error("Error changing password:", error);
          alert("Failed to change password. Please try again.");
        },
      });
  }

  saveProfile() {
    this.customerService.updateProfile(this.customer).subscribe({
      next: (response) => {
        this.popup.showPopup("Profile updated successfully!");
        this.isEditMode = false;
      },
      error: (error) => {
        console.error("Error updating profile:", error);
        this.popup.showPopup("Failed to update profile. Please try again.");
      },
    });
  }
}
