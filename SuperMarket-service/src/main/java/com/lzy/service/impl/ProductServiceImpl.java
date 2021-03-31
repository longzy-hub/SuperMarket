package com.lzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.lzy.dto.SearchProductDto;
import com.lzy.error.BusinessException;
import com.lzy.mapper.CategoryMapper;
import com.lzy.mapper.ProductImgMapper;
import com.lzy.mapper.ProductMapper;
import com.lzy.page.PageQuery;
import com.lzy.pojo.Category;
import com.lzy.pojo.Product;
import com.lzy.pojo.ProductImg;
import com.lzy.service.ProductService;
import com.lzy.utils.ImageUtils;
import com.lzy.validator.ValidatorApi;
import com.lzy.viewobject.ProductVo;
import com.lzy.viewobject.SearchProductVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ValidatorApi validator;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductImgMapper productImgMapper;

    @Resource
    private UserServiceImpl userService;


    // 查询所有分类
    @Override
    public List<Category> categoryList() {
        List<Category> categories = categoryMapper.selectList(null);
        return categories;
    }

    // 根据分类id查询商品
    @Override
    public IPage<Product> productPageByCategoryId(int categoryId, SearchProductVo productVo, PageQuery pageQuery) {
        // 注解检验
        validator.check(pageQuery);
        // 将vo赋值给dto
        SearchProductDto dto = new SearchProductDto();
        if (StringUtils.isNotBlank(productVo.getKeyword())){
            dto.setKeyword(productVo.getKeyword());
        }
        if (StringUtils.isNotBlank(productVo.getSearch_status())){
            dto.setSearch_status(Integer.parseInt(productVo.getSearch_status()));
        }
        // mybatis-plus 的查询条件
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(String.valueOf(categoryId))){
            queryWrapper.eq("category_id", categoryId);
        }
        if (StringUtils.isNotBlank(dto.getKeyword())){
            // 模糊查询 %name%
            queryWrapper.like("product_name", dto.getKeyword());
        }
        if (StringUtils.isNotBlank(String.valueOf(dto.getSearch_status()))){
            queryWrapper.eq("product_status", dto.getSearch_status());
        }
        // 统计符合条件的数据
        int count = productMapper.selectCount(queryWrapper);
        IPage<Product> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (count > 0){
            page = productMapper.selectPage(page,queryWrapper.orderByDesc("id"));
            return page;
        }
        return page;
    }

    // 添加商品
    @Override
    public void insertProduct(ProductVo productVo) throws IOException {
         validator.check(productVo);
//         System.out.println(productVo.getProductName());
//         System.out.println(productVo.getProductImg());

        // 商品去重
        if (checkProductNameExist(productVo.getProductName(), productVo.getId())){
            throw new BusinessException("商品已经存在");
        }

        // 建造者模式
        Product entity = Product.builder().productName(productVo.getProductName())
                .describes(productVo.getDescribes()).productStatus(productVo.getProductStatus())
                .categoryId(productVo.getCategoryId()).imgId(returnImg(productVo.getProductImg())).price(productVo.getPrice())
                .creator(userService.getPrincipal()).createTime(new Date()).updateTime(new Date()).build();
        // 添加商品
        productMapper.insert(entity);

    }

    // 更新商品
    @Override
    public void updateProduct(ProductVo productVo) throws IOException {
        // 注解检验
        validator.check(productVo);
        // 根据id查询商品是否存在
        Product before = productMapper.selectById(productVo.getId());
        Preconditions.checkNotNull(before, "待更新的商品不存在");
        // 商品去重
        if (checkProductNameExist(productVo.getProductName(), productVo.getId())){
            throw new BusinessException("该商品已经存在");
        }
        //建造者模式
        Product entity = Product.builder().id(productVo.getId()).productName(productVo.getProductName())
                .categoryId(productVo.getCategoryId()).describes(productVo.getDescribes())
                .price(productVo.getPrice()).productStatus(productVo.getProductStatus())
                .creator(userService.getPrincipal()).updateTime(new Date()).build();
        // 判断是否改变图片
        if (StringUtils.isNotBlank(productVo.getProductImg().getOriginalFilename())){
            entity.setImgId(returnImg(productVo.getProductImg()));
        }
        // 更新商品
        productMapper.updateById(entity);
    }

    // 查询所有图片
    @Override
    public List<ProductImg> imgList() {
        List<ProductImg> imgs = productImgMapper.selectList(null);
        return imgs;
    }

    // 单个删除商品
    @Override
    public void delProduct(ProductVo productVo) {
        productMapper.deleteById(productVo.getId());
    }

    // 批量删除商品
    @Override
    public void batchDelProduct(int[] ids) {
        if (null != ids && ids.length > 0){
            for (int id : ids){
                productMapper.deleteById(id);
            }
        }
    }

    // 商品去重
    private boolean checkProductNameExist(String productName, Integer id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_name", productName);
        if (null != id){
            queryWrapper.ne("id", id);
        }
        return productMapper.selectCount(queryWrapper) > 0;
    }

    // 添加图片到数据库
    private Integer returnImg(MultipartFile productImg) throws IOException {
        // 文件名
        String imageName = ImageUtils.uploadImg(productImg);
        // 判断是否为空
        if (StringUtils.isNotBlank(imageName)){
            ProductImg img = ProductImg.builder().imgName(imageName).build();
            productImgMapper.insert(img);
        }
        // 获取图片id
        QueryWrapper<ProductImg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("img_name", imageName);
        int id = productImgMapper.selectOne(queryWrapper).getId();
        return id;
    }


}
