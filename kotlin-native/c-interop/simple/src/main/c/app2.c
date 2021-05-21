#include "4.h"

#define T_(x) static_kref_ ## x
#define PV_(x) { .pinned = ((static_KNativePtr)(x)) }
#define CAST(to, v) ((to).pinned = (v).pinned)

int main() {
  static_ExportedSymbols *symbols = static_symbols();
  T_(CFoo1)  foo1 = symbols->kotlin.root.CFoo1.CFoo1();
  T_(CFoo2)  foo2 = symbols->kotlin.root.CFoo2.CFoo2();
  T_(kotlin_Int) intV = symbols->createNullableInt(42);
  T_(kotlin_Any) a;
  CAST(a, foo1);
  symbols->kotlin.root.CFoo1.callMe(foo1, a);

  CAST(a, foo2);
  symbols->kotlin.root.CFoo1.callMe(foo1, a);

  CAST(a, intV);
  symbols->kotlin.root.CFoo1.callMe(foo1, a);

  return 0;
}
