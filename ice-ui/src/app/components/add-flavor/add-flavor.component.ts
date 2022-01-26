import { FlavorCategory } from './../../models/flavor-category';
import { Flavor } from './../../models/flavor';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { FlavorsService } from '../../services/flavors.service';
import { FlavorMappingService } from '../../services/flavor-mapping.service';
import { ErrorService } from 'src/app/services/error.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorResponse } from 'src/app/models/error-response';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-flavor',
  templateUrl: './add-flavor.component.html',
  styleUrls: ['./add-flavor.component.css']
})
export class AddFlavorComponent implements OnInit {

  supportedCategories = FlavorMappingService.supportedCategories;


  constructor(  private flavorsService: FlavorsService,
                private errorService: ErrorService,
                private fb: FormBuilder,
                private router: Router) { }

  flavorFormGroup = this.fb.group( {
    name: new FormControl(''),
    category: new FormControl(''),
    ingredients: new FormArray([]),
    foodIntolerance: new FormControl(''),
    nutritionalValue: new FormControl(''),
    price: new FormControl('')
  });

  get ingredients() {
    return this.flavorFormGroup.controls["ingredients"] as FormArray;
  }

  ngOnInit(): void {
  }

  addIngredient() {
    const ingredientForm = this.fb.control('', Validators.required);
    
    this.ingredients.push(ingredientForm);
  }

  deleteIngredient(index: number) {
    this.ingredients.removeAt(index);
}

  async saveData(){
    console.log(this.flavorFormGroup);
    await this.flavorsService.addFlavor(this.flavorFormGroup.value as Flavor)
    .then( _ => this.router.navigate(['list']))
    .catch( (error: HttpErrorResponse) => {
      console.log(error)
      this.errorService.handleConnectionError(error);
    });
  }
}
