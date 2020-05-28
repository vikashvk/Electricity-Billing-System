import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerInteractionComponent } from './customer-interaction.component';

describe('CustomerInteractionComponent', () => {
  let component: CustomerInteractionComponent;
  let fixture: ComponentFixture<CustomerInteractionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerInteractionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerInteractionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
