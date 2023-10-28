import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Validators, FormBuilder, FormControl, FormArray } from '@angular/forms';
import { FlavorCategory, NewFlavor } from 'src/app/core/api/v1';
import { Router } from '@angular/router';
import { FlavorFacade } from 'src/app/store/flavor-facade';
import { CallState } from 'src/app/store/flavor-models';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

interface FlavorFormGroupForm {
  name: FormControl<string>;
  category: FormControl<string>;
  ingredients: FormArray<FormControl<string>>;
  foodIntolerance: FormControl<string>;
  nutritionalValue: FormControl<number>;
  price: FormControl<string>;
};

@Component({
  selector: 'app-add-flavor',
  templateUrl: './add-flavor.component.html',
  styleUrls: ['./add-flavor.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AddFlavorComponent {
  protected CallState = CallState;
  categories = Object.values(FlavorCategory);
  newFlavorState$ = this.flavorFacade.newFlavorState$

  constructor(  
    private fb: FormBuilder,
    private router: Router,
    private flavorFacade: FlavorFacade
    ) {
      this.flavorFacade.newFlavorState$.pipe(takeUntilDestroyed()).subscribe((newFlavorState) => {
        console.log(newFlavorState)
        if(newFlavorState === CallState.SUCCESS) {
          console.log('sadasd')
          this.flavorFacade.resetNewFlavorsState();
          this.router.navigate(['list']);
        }
      });
    }

  flavorFormGroup = this.fb.group<FlavorFormGroupForm>( {
    name: this.fb.nonNullable.control<string>('', Validators.required),
    category: this.fb.nonNullable.control<string>('', Validators.required),
    ingredients: this.fb.nonNullable.array<string>([]),
    foodIntolerance: this.fb.nonNullable.control<string>('', Validators.required),
    nutritionalValue: this.fb.nonNullable.control<number>(0, Validators.required),
    price: this.fb.nonNullable.control<string>('', Validators.required),
  });

  get ingredients() {
    return this.flavorFormGroup.controls["ingredients"];
  }

  addIngredient() {
    this.ingredients.push(this.fb.nonNullable.control<string>('', Validators.required));
  }

  deleteIngredient(index: number) {
    this.ingredients.removeAt(index);
  }

  onSubmit(){
    this.flavorFacade.addFlavor(this.flavorFormGroup.getRawValue() as NewFlavor);
  }
}
