import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { AddProductComponent } from './pages/add-product/add-product.component';
import { ViewProductsComponent } from './pages/view-products/view-products.component';
import { ViewCustomersComponent } from './pages/view-customers/view-customers.component';
import { ViewOrdersComponent } from './pages/view-orders/view-orders.component';
import { CartComponent } from './pages/cart/cart.component';
import { PaymentsComponent } from './pages/payments/payments.component';
import { OrderComponent } from './pages/order/order.component';
import { ReceiptComponent } from './pages/receipt/receipt.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { ProfileComponent } from './pages/profile/profile.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'add-product', component: AddProductComponent },
  { path: 'all-products', component: ViewProductsComponent },
  { path: 'all-customers', component: ViewCustomersComponent },
  { path: 'all-orders', component: ViewOrdersComponent },
  { path: 'cart', component: CartComponent },
  { path: 'payment', component: PaymentsComponent },
  { path: 'orders', component: OrderComponent },
  { path: 'receipt', component: ReceiptComponent },
  { path: 'register', component: RegistrationComponent },
  { path : 'userProfile', component: ProfileComponent }
];
