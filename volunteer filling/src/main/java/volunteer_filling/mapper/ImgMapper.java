package volunteer_filling.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import volunteer_filling.entity.Img;

@Mapper
public interface ImgMapper {
  @Select("select id, path, owner from img where owner = #{ownerName}")
  public List<Img> getImgByOwner(String ownerName);

  @Insert("INSERT INTO img (path, owner) VALUES (#{path}, #{owner});\n")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  public int insertImg(Img img);

  @Select("SELECT path FROM  img WHERE owner = #{owner}")
  String  getPath(String owner);

  @Delete("DELETE  FROM  img WHERE owner = #{owner}")
  Boolean deleteData(String name);
}
