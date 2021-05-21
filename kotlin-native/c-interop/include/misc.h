#ifndef __MISC_H__
#define __MISC_H__

#define CONCAT_(x, y) x ## y
#define CONCAT(x,y) CONCAT_(x, y)
#define symbols CONCAT(PREFIX, _symbols())
#define T_(x) CONCAT(PREFIX , _kref_ ## x)
#define PV_(x) { .pinned = (( PREFIX ## _KNativePtr)(x)) }
#define CAST(to, v) ((to).pinned = (v).pinned)

#define KN_(x) CONCAT(PREFIX, _symbols())->x

#endif