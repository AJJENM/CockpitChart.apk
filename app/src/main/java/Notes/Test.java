//package com.example.myapplication;
//
//public class Test {
//
////    值 /  总 = 面积 / 总面积
////const 关键字，表示一个常量
//// const pi = 3.14;
///**
// * @param {Array} children 待layout的矩形面积数组
// * @param {Array} row 正要layout的矩形面积数组
// * @param {Number} minSide 短边
// */
//  const squarify = (children, row, minSide) => {
//        // 递归出口
//        if (children.length === 1) {
//            return layoutLastRow(row, children, minSide);
//        }
//
//    const rowWithChild = [...row, children[0]];
//        // 当正在layout的矩形数组row为空
//        // 或者 加上children[0]的rowWithChild最差长宽比 相比于row 更好（接近1）
//        // 这里可能会有点难理解，其实此处的逻辑对应上面描述的第三步。
//        // 满足此条件，采用同行同列（到底是同行还是同列，取决于短边是哪个）方式填充，否则新建一行/一列
//        if (row.length === 0 || worstRatio(row, minSide) >= worstRatio(rowWithChild, minSide)) {
//            // 将children[0]从children删除
//            children.shift();
//            // 递归执行 squarify
//            return squarify(children, rowWithChild, minSide);
//        } else {
//            // 将 row 内的小矩形绘制出来
//            layoutRow(row, minSide, getMinSide().vertical);
//            return squarify(children, [], getMinSide().value);
//        }
//    };
//
//    /**
//     * 最差长宽比（公式见参考1，此处不再推理）
//     * @param {Array} row
//     * @param {Number} minSide 短边
//     */
//    function worstRatio(row, minSide) {
//    const sum = row.reduce(sumReducer, 0);
//    const rowMax = getMaximum(row);
//    const rowMin = getMinimum(row);
//        return Math.max(((minSide ** 2) * rowMax) / (sum ** 2), (sum ** 2) / ((minSide ** 2) * rowMin));
//    }
//
//  const Rectangle = {
//        data: [],
//        xBeginning: 0,
//                yBeginning: 0,
//                totalWidth: canvasWidth,
//                totalHeight: canvasHeight,
//    };
//    /**
//     * 获取最短边，若高为短边，则垂直，否则水平
//     */
//  const getMinSide = () => {
//        if (Rectangle.totalHeight > Rectangle.totalWidth) {
//            return { value: Rectangle.totalWidth, vertical: false };
//        }
//        return { value: Rectangle.totalHeight, vertical: true };
//    };
//
//    /**
//     * 计算在要layout的row中每个矩形的坐标(x,y,width,height)
//     * 为方便理解，这里仅展示vertical为true的代码，即短边为高
//     * @param {Array} row
//     * @param {Number} height 短边
//     * @param {Boolean} vertical 是否垂直布局
//     */
//   const layoutRow = (row, height, vertical) => {
//        // 先算总面积，除以高（短边），可得宽
//        // 因小矩形是沿着高填充的，则所有row中小矩形的高之和等于Rectangle中的高，小矩形宽为rowWidth
//        // 什么叫做沿高填充见下注释图，小矩形A和小矩形B为沿高填充
//        //  _______________________________
//        // |     A      |                  |
//        // |____________|                  |
//        // |_____B______|__________________|
//        // {--rowWidth--}
//    const rowWidth = row.reduce(sumReducer, 0) / height;
//        row.forEach((rowItem) => {
//      const rowHeight = rowItem / rowWidth; // 小矩形的高
//      const { xBeginning } = Rectangle;
//      const { yBeginning } = Rectangle;
//        let data;
//        if (!vertical) {
//            // 小矩形的位置信息，
//            data = {
//                    x: xBeginning,
//                    y: yBeginning,
//                    width: rowWidth,
//                    height: rowHeight,
//        };
//            // 移动 yBeginning，以绘制下一个小矩形
//            Rectangle.yBeginning += rowHeight;
//        }
//        Rectangle.data.push(data);
//    });
//        // row 内的小矩形绘制完成后
//        // 重置xBeginning，yBeginning，totalWidth 的值
//        if (vertical) {
//            Rectangle.xBeginning += rowWidth;
//            Rectangle.yBeginning -= height;
//            Rectangle.totalWidth -= rowWidth;
//        }
//    };
//
//}
//
//
