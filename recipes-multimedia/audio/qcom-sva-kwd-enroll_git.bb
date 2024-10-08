inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "sva-kwd_enroll"

DEPENDS += "qcom-sva-ml-commondwarf2 qcom-sva-common qcom-sva-aml"

PBT_ARCH = "armv8-2a"

SRC_URI[armv8-2a.sha256sum] = "1b9d6dc9896220e6daaf571a66152a432da002ad4316132d2c467e75c796c6b4"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"
