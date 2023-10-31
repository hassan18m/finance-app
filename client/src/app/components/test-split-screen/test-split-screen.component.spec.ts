import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestSplitScreenComponent } from './test-split-screen.component';

describe('TestSplitScreenComponent', () => {
  let component: TestSplitScreenComponent;
  let fixture: ComponentFixture<TestSplitScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TestSplitScreenComponent]
    });
    fixture = TestBed.createComponent(TestSplitScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
