import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFlavorComponent } from './components/add-flavor/add-flavor.component';
import { ListFlavorComponent } from './components/list-flavor/list-flavor.component';

const routes: Routes = [
  {
    path: 'add',
    component: AddFlavorComponent
  },
  {
    path: 'list',
    component: ListFlavorComponent
  },
  {
    path: '',
    component: ListFlavorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
