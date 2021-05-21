#include "../../../app1_api.h"
#define PREFIX app1
#include "../../../../include/misc.h"

int main() {
  T_(CFoo1)  cfoo1 = KN_(kotlin.root.CFoo1.CFoo1());
  symbols->DisposeStablePointer(cfoo1.pinned);
}
