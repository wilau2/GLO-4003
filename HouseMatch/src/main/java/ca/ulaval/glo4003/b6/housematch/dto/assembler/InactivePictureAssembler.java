package ca.ulaval.glo4003.b6.housematch.dto.assembler;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.dto.InformationPictureDto;

public class InactivePictureAssembler {

   public Picture assembleInactivePicture(InformationPictureDto inactivePictureDto) {

      Picture inactivePicture = new Picture(inactivePictureDto.getUid(), inactivePictureDto.getAddress(),
            inactivePictureDto.getName(), inactivePictureDto.getActive());
      return inactivePicture;
   }

   InformationPictureDto assembleInactivePictureDto(Picture inactivePicture) {

      InformationPictureDto inactivePictureDto = new InformationPictureDto(inactivePicture.getUid(),
            inactivePicture.getAddress(), inactivePicture.getName(), inactivePicture.isActive().toString());
      return inactivePictureDto;
   }

}
