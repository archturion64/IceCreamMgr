import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFlavorComponent } from './components/add-flavor/add-flavor.component';
import { ListFlavorComponent } from './components/list-flavor/list-flavor.component';
import { flavorGuard } from './guards/flavor.guard';

const routes: Routes = [
  {
    path: 'add',
    component: AddFlavorComponent,
    canActivate: [flavorGuard]
  },
  {
    path: 'list',
    component: ListFlavorComponent,
    canActivate: [flavorGuard]
  },
  {
    path: '',
    component: ListFlavorComponent,
    canActivate: [flavorGuard],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }