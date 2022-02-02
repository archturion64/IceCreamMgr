import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFlavorComponent } from './components/add-flavor/add-flavor.component';
import { ListFlavorComponent } from './components/list-flavor/list-flavor.component';
import { FlavorsResolver } from './resolvers/flavors.resolver';

const routes: Routes = [
  {
    path: 'add',
    component: AddFlavorComponent
  },
  {
    path: 'list',
    component: ListFlavorComponent,
    resolve: { flavorsData: FlavorsResolver }
  },
  {
    path: '',
    component: ListFlavorComponent,
    resolve: { flavorsData: FlavorsResolver }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
